package mx.tec.pruebabrillamostodas3.model

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

/**
 * @Author Carlos Iker Fuentes Reyes
 */

class ModelConnection
{

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
    private val key = generateAESKey(256)
    private val addUserEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/add/user"
    private val addOrderEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/connection/add/order"
    private val getProductListEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/get_productos/"
    private val getJWTKeyEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/auth/getToken/"
    private val getProductImageEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/product_images/"
    private val getProductsWithTokenEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/connection/productos/"

    fun createDataInfo(productos: MutableList<Pair<Producto,Int>>) : String{
        var id_productos : String = ""
        var cantidad_producto : String = ""

        for (producto in productos){
            id_productos+= "${producto.first.id},"
            cantidad_producto += "${producto.second},"
        }
        val result = id_productos.dropLast(1) + ";" + cantidad_producto.dropLast(1)

        return result
    }
    fun encryptPassword(password: String, key: SecretKey) : String{
        val encryptedPassword = aesEncrypt(password.toByteArray(), key)
        return encryptedPassword.toString()
    }

    fun createUser(nombre: String, apellido_paterno: String, apellido_materno: String, fecha_nacimiento: String, correo: String, password: String) : Usuario{
        return Usuario(nombre, apellido_paterno, apellido_materno, fecha_nacimiento, correo, encryptPassword(password, key))
    }

    suspend fun addUser(user:Usuario) : HttpResponse {
        val response : HttpResponse = client.post(addUserEndpoint){
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        return response
    }



    suspend fun addOrderWithToken(order:Order,user:Usuario) : HttpResponse {
        val userToken : String = getJWTKey(user.email,user.password).data
        val response : HttpResponse = client.post(addOrderEndpoint){
            contentType(ContentType.Application.Json)
            setBody(order)
            url{
                parameters.append("user_token",userToken)
            }
        }
        return response
    }

    suspend fun getProductList(url:String) : List<Producto>{
        val response : HttpResponse = client.get(url)
        val producto : ListaProducto = response.body()
        return producto.productos
    }

    suspend fun getJWTKey(user: String, password: String) : JWT_KEY{
        val response : HttpResponse = client.get(getJWTKeyEndpoint){
            url {
                parameters.append("in_email", user)
                parameters.append("in_password", password)
            }
        }
        return response.body()
    }

    suspend fun getProductsWithToken(user: Usuario) : List<Producto>{
        val userToken : String = getJWTKey(user.email,user.password).data
        val response : HttpResponse = client.get(getProductsWithTokenEndpoint){
            url {
                parameters.append("user_token", userToken)
            }
        }
        val producto : ListaProducto = response.body()
        return producto.productos
    }

    suspend fun getProductImage(imageId : Int) : ByteArray{
        val response : HttpResponse = client.get(getProductImageEndpoint) {
            url {
                parameters.append("image_id", imageId.toString())
            }
        }

        if(response.status.value == 200){
            return response.body()
        }
        return ByteArray(0)
    }


}