package mx.tec.pruebabrillamostodas3.model
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import kotlinx.serialization.Serializable

import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.client.request.post
import retrofit2.Call
import javax.crypto.SecretKey


val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
}
private val key = generateAESKey(256)
private val addUserEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/add/user"
private val addOrderEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/connection/add/order"
private val getProductListEndpoint = "https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/get_productos/"

suspend fun addUser(url:String,user:Usuario) : HttpResponse{
    val response : HttpResponse = client.post(url){
        contentType(ContentType.Application.Json)
        setBody(user)
    }
    return response
}

suspend fun addOrder(url:String, order:Order) : HttpResponse{
    val response : HttpResponse = client.post(url){
        contentType(ContentType.Application.Json)
        setBody(order)
    }

    return response
}

fun createDataInfo(productos: MutableList<Pair<Producto,Int>>) : String{
    var id_productos : String = ""
    var cantidad_producto : String = ""

    for (producto in productos){
        id_productos+= "${producto.first.id},"
        cantidad_producto += "${producto.second},"
    }
    var result = id_productos.dropLast(1) + ";" + cantidad_producto.dropLast(1)

    return result
}
suspend fun getProductList(url:String) : List<Producto>{
    val response : HttpResponse = client.get(url)
    val producto : ListaProducto = response.body()
    return producto.productos
}
suspend fun getJWTKey(user: String, password: String) : JWT_KEY{
    val response : HttpResponse = client.get("https://apex.oracle.com/pls/apex/todasbrillamos/auth/getToken/") {
        url {
            parameters.append("in_email", user)
            parameters.append("in_password", password)
        }
    }
    return response.body()
}
fun encryptPassword(password: String, key: SecretKey) : String{
    val encryptedPassword = aesEncrypt(password.toByteArray(), key)
    return encryptedPassword.toString()
}

suspend fun getDataWithToken() : List<Producto>{
    val response : HttpResponse = client.post("https://apex.oracle.com/pls/apex/todasbrillamos/hr/empinfo/"){
        contentType(ContentType.Application.Json)
        setBody(TokenData(getJWTKey("ala25@gmail.com","1234554").data))
    }
    val producto : ListaProducto = response.body()
    return producto.productos
}
fun createUser(nombre: String, apellido_paterno: String, apellido_materno: String, fecha_nacimiento: String, correo: String, password: String) : Usuario{
    return Usuario(nombre, apellido_paterno, apellido_materno, fecha_nacimiento, correo, encryptPassword(password, key))
}
suspend fun getProductImage(imageId : Int) : Pair<String,ByteArray>?{
    val response : HttpResponse = client.get("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/product_images/?image_id="+imageId.toString())

    if(response.status.value == 200){
        return Pair(response.contentType().toString(),response.body())
    }
    return null
}

suspend fun getProductsWithToken() : List<Producto>{
    val myKey : String = getJWTKey("ala25@gmail.com","1234554").data
    val response : HttpResponse = client.get("https://apex.oracle.com/pls/apex/todasbrillamos/connection/productos/"){
        url {
            parameters.append("user_token", myKey)
        }
    }
    val producto : ListaProducto = response.body()
    return producto.productos
}
suspend fun addOrderWithToken(order:Order,user:Usuario) : HttpResponse {
    val userToken : String = getJWTKey(user.email,user.password).data
    val response : HttpResponse = client.post("https://apex.oracle.com/pls/apex/todasbrillamos/connection/add/order"){
        contentType(ContentType.Application.Json)
        setBody(order)
        url{
            parameters.append("user_token",userToken)
        }
    }
    return response
}

suspend fun main(){

    val user = Usuario("Cesar","Dia","Jueves","06-MAR-2003","cesar@gmail.com","1234554")
    //println(addOrder("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/add/order"))
    println(addUser("https://apex.oracle.com/pls/apex/todasbrillamos/connection/add/user",user))
    var p : MutableList<Pair<Producto,Int>> = mutableListOf()
    val aes = AESEncryption()
    println(aes.generateAESKey(256))

    //print(createDataInfo(p))
    //print(getJWTKey("alan25@gmail.com","1234554"))
    //print(encryptPassword("1234554",generateAESKey(256)))
    //println(getDataWithToken())
    //println("________________--")
    //println(createUser("Alan","Vega","Reza","06-MAR-2003","ala25@gmail.com","1234554"))

    println(addOrderWithToken(Order(createDataInfo(p),201),Usuario("Alan","Vega","Reza","06-MAR-2003","ala25@gmail.com","1234554")))
    //println(getProductsWithToken())
    /*val imageData = getProductImage(17)
    if (imageData != null) {
        val (mediaType, imageBytes) = imageData
        println("Media Type: $mediaType")

        // You can now use the imageBytes as needed, e.g., save to a file or send in a response
    } else {
        println("Image not found or failed to retrieve.")
    }*/
    //getProductList("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/add/get_productos/")
}
