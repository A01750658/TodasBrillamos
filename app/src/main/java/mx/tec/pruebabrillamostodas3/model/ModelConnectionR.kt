package mx.tec.pruebabrillamostodas3.model

/**
 * @author Carlos Iker Fuentes Reyes
 * Clase que se encarga de la conexión con el servidor haciendo uso de retrofit y de la clase
 * abstracta TodasBrillamosAPI
 */

import com.google.gson.Gson
import io.ktor.client.statement.HttpResponse
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest


class ModelConnectionR {
    //Instancia de retrofit con el URL base de la API
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://apex.oracle.com/pls/apex/todasbrillamos/connection/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Servicio que llamará a las funciones de la clase a
    private val service by lazy {
        retrofit.create(TodasBrillamosAPI::class.java)
    }

    //Product functions
    /**
     * Función que descarga los productos de la base de datos y genera un Pair con la cantidad y la lista de productos
     * @param userToken: Token del usuario
     * @return [Pair]: Pair con la cantidad [Int] y la lista de productos [ListaProducto]
     */
    suspend fun getProductsWithToken(userToken: String): Pair<Int, List<Producto>> {
        val response: ListaProducto = service.getProductos(userToken)
        val return_value: Pair<Int, List<Producto>> =
            Pair(response.cantidad, response.productos)
        return return_value
    }

    /**
     * Función que descarga la imagen de un producto con base en su id
     * @param imageId: Id de la imagen
     * @return [ByteArray]: Imagen
     */
    suspend fun getProductImage(imageId: Int): ByteArray {
        val response: ResponseBody = service.getProductImage(imageId.toString())
        return response.bytes()
    }

    //User Functions
    /**
     * Función que agrega un usuario a la base de datos
     * @param user[Usuario]: Usuario a agregar
     * @return [ResponseFormat]: Respuesta del servidor
     */
    suspend fun signUp(user: Usuario): ResponseFormat {
        val response: ResponseFormat = service.addUser(user)
        return response
    }

    /**
     * Función que obtiene el token de un usuario
     * @param user[String]: Email del usuario
     * @param password[String]: Contraseña del usuario
     * @return [JWT_KEY]: Token del usuario
     */

    suspend fun getJWTKey(user: String, password: String): JWT_KEY {
        val response: JWT_KEY = service.getJWTKey(user, password)
        return response
    }

    /**
     * Función que agrega una dirección a la base de datos
     * @param user_token [String]: Token del usuario
     * @param address [Direccion]: Dirección a agregar
     * @return [ResponseFormat] : respuesta del servidor
     */
    suspend fun addAddress(user_token: String, address: Direccion): ResponseFormat {
        val response: ResponseFormat = service.addAddressWithToken(address, user_token)
        return response
    }

    /**
     * Función que actualiza una dirección a la base de datos
     * @param user_token [String]: Token del usuario
     * @param address [Direccion]: Dirección a agregar
     * @return [ResponseFormat] : respuesta del servidor
     */
    suspend fun updateAddress(user_token: String, address: Direccion): ResponseFormat {
        val response: ResponseFormat = service.updateAddressWithToken(address, user_token)
        return response
    }

    /**
     * Función que obtiene los datos del usuario
     * @param user_token [String]
     * @return [DataUsuario] : la información del usuario
     */
    suspend fun getUserData(user_token: String) : DataUsuario{
        val response : DataUsuario = service.getUserDataWithToken(user_token)
        return response
    }

    //Password Recovery Functions
    /**
     * Función que genera un hash de una contraseña
     * @param password[String]: Contraseña a hashear
     * @return [String]: Hash de la contraseña
     */
    fun hash(password: String): String {
        val bytes = password.toByteArray()

        val md = MessageDigest.getInstance("SHA-256")
        println(md)
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    /**
     * Función que obtiene el token de recuperación de contraseña
     * @param email[String]: Email del usuario
     * @return [ResponseFormat]: Respuesta del servidor
     */
    suspend fun getRecoveryPasswordToken(email: String): ResponseFormat {
        val response: ResponseFormat = service.getRecoveryPasswordToken(email)
        return response
    }

    /**
     * Función que cambia la contraseña de un usuario
     * @param code[Int]: Código de recuperación de contraseña
     * @param email[String]: Email del usuario
     * @param password[String]: Nueva contraseña
     */
    suspend fun changePassword(code: Int, email: String, password: String): ResponseFormat {
        val response: ResponseFormat = service.changePassword(ChangePassword(code, email, password))
        return response
    }

    //Order Functions
    /**
     * Función que acomoda la información de una lista de productos en un string para mandar a la base de datos
     * @param productos[MutableList<Pair<Int,Int>>]: Lista de productos a enviar
     * @return [String]: String con la información de los productos
     */
    fun createDataInfo(productos: MutableList<Pair<Int, Int>>): String {
        var id_productos: String = ""
        var cantidad_producto: String = ""
        for (producto in productos) {
            id_productos += "${producto.first},"
            cantidad_producto += "${producto.second},"
        }
        val result = id_productos.dropLast(1) + ";" + cantidad_producto.dropLast(1)
        return result
    }

    /**
     * Función que agrega una orden a la base de datos
     * @param order[Order]: Orden a agregar
     * @param userToken [String]
     * @return [ResponseFormat]: Respuesta del servidor
     */
    suspend fun addOrderWithToken(order: Order, userToken: String): ResponseFormat {
        val response : ResponseFormat = service.addOrderWithToken(order, userToken)
        return response
    }

    /**
     * Función que obtiene la información de las ordenes que ha realizado un usuario
     * @param userToken [String] Token del usuario
     * @return [HashMap<Int,List<Orderproducts>>]: Mapa con las ordenes del usuario
     */
    suspend fun getOrderInfo(userToken: String) : HashMap<Int,List<Orderproducts>>{

        val response : Orders = service.getOrderInfoWithToken(userToken)
        val userOrders : HashMap<Int,List<Orderproducts>> = hashMapOf()
        for (order in response.ordenes){
            userOrders[order.id_orden] = order.orden
        }
        return userOrders
    }
    //Función de foros
    /**
     * Función que obtiene la información de los foros
     * @param userToken [String] token del usuario obtenido por medio de la función getJWTKey
     * @return [ListaForo] : información de los foros
     */
    suspend fun getForo(userToken: String) : ListaForo{
        val response : ListaForo = service.getForoWithToken(userToken)
        return response
    }
    /**
     * Función que obtiene los comentarios de un foro
     * @param userToken [String] token del usuario obtenido por medio de la función getJWTKey
     * @return [ListaComentario] : lista de comentarios
     */
    suspend fun getComments(userToken: String,id:Int) : ListaComentario{
        val response : ListaComentario = service.getCommentWithToken(userToken,id)
        return response
    }
    suspend fun addForo(userToken: String,pregunta:String) : ResponseFormat{
        val foro : SolicitudForo = SolicitudForo(pregunta)
        val response : ResponseFormat = service.addForumSolicitationWithToken(foro,userToken)
        return response
    }
}

suspend fun main(){
    val modelConnection = ModelConnectionR()
    //println(modelConnection.hash("AAAAA"))
    val lista : List<Producto> = modelConnection.getProductsWithToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJPUkRTIiwic3ViIjoiaWtlckBnbWFpbC5jb207MTIzNCIsImlhdCI6MTcyNjQ5ODg3NiwiZXhwIjoxNzI2NDk5MTc2fQ.KCwualrayru-DSqHG-Zi5IpXc3TPx8LDb1EvKOtWymA").second
    //println(lista)
//val p :MutableList<Pair<Int,Int>> = mutableListOf()
    /*for (i in lista){
        p.add(Pair(i.id,1))
    }*/
    //val dInfo = modelConnection.createDataInfo(p)
    //val order = Order(dInfo,201)
    //val us : Usuario = Usuario("Iker","Fuentes","Reyes","16-DEC-2002","iker23537676565877654@gmail.com","1234",0,1,"5532532512")
    //println(modelConnection.addOrderWithToken(order,modelConnection.getJWTKey(us.email,us.password).data))
    //println(modelConnection.addAddress(modelConnection.getJWTKey(us.email,us.password).data,Direccion("Convento de SantaMaría","Jardines de ","Tlane","EdoMex","54050","12","12",2)))
    // val us : Usuario = Usuario("Iker","Fuentes","Reyes","16-DEC-2002","iker2365@gmail.com","1234",0)
    //println(modelConnection.getJWTKey(us.email,us.password))
    //println(modelConnection.signUp(us))
    //println(modelConnection.getUserData(modelConnection.getJWTKey("iker@gmail.com","1234").data))
    //println(modelConnection.getRecoveryPasswordToken("iker.fuentesreyes@gmail.com"))
    //println(modelConnection.changePassword(109076427,"iker.fuentesreyes@gmail.com","777"))
    println(modelConnection.getOrderInfo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJPUkRTIiwic3ViIjoiaWtlckBnbWFpbC5jb207MTIzNCIsImlhdCI6MTcyODI2NjIwOCwiZXhwIjoxNzI4MjY2MjU4fQ.HzwRrR7_vQ3tYzvZmzpVj_22sM7QYde04RipRrXN2Zw"))
    //println(modelConnection.getForo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJPUkRTIiwic3ViIjoiaWtlci5mdWVudGVzcmV5ZXNAZ21haWwuY29tO3Jvb3QiLCJpYXQiOjE3Mjc4OTQ4MjksImV4cCI6MTcyNzg5NTEyOX0.CS7cV7M4NgdydxJwZPBKrXnf6wjJ61YbRy8QRZ7jmNM"))
    //println(modelConnection.getComments("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJPUkRTIiwic3ViIjoiaWtlci5mdWVudGVzcmV5ZXNAZ21haWwuY29tO3Jvb3QiLCJpYXQiOjE3Mjc4OTQ4MjksImV4cCI6MTcyNzg5NTEyOX0.CS7cV7M4NgdydxJwZPBKrXnf6wjJ61YbRy8QRZ7jmNM",161))
}