package mx.tec.pruebabrillamostodas3.model

import io.ktor.client.statement.HttpResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Carlos Iker Fuentes Reyes
 * Clase abstracta para la funcionalidad de retrofit,
 * que define los endpoints de la API
 * y los métodos para acceder a ellos.
 */


const val addUserEndpoint = "add/user"
const val addOrderwithTokenEndpoint = "add/order"
const val getJWTKeyEndpoint = "getToken/"
const val getProductImageEndpoint = "product_images/"
const val getProductsWithTokenEndpoint = "productos/"
const val addAddressWithTokenEndpoint = "add/address"
const val getUserDataWithTokenEndpoint = "get/user_data"
const val getRecoveryPasswordTokenEndpoint = "recover/password/get_token"
const val changePasswordEndpoint = "recover/password/change_password"
const val getOrderInfoWithTokenEndpoint = "get_order/"
const val updateAddressWithTokenEndpoint = "edit/address/"
interface TodasBrillamosAPI
{


    @Headers(
        "User-Agent:Retrofit"
    )
    /**
    Función que descarga los productos de la base de datos
     @param user_token: Token del usuario
     @return [ListaProducto]: Lista de productos
     */
    @GET(getProductsWithTokenEndpoint)
    suspend fun getProductos( @Query("user_token") user_token : String): ListaProducto

    @Headers(
        "User-Agent:Retrofit"
    )
    /**
    Función que descarga la imagen de un producto
     @param image_id: Id de la imagen
     @return [ResponseBody]: Imagen. Response body es un formato estandar de respuesta del JSON
     */
    @GET(getProductImageEndpoint)
    suspend fun getProductImage(@Query("image_id") image_id : String): ResponseBody

    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que agrega un usuario a la base de datos
     * @param user: Usuario a agregar
     * @return [ResponseFormat]: Respuesta del servidor.
     */
    @POST(addUserEndpoint)
    suspend fun addUser(@Body user: Usuario): ResponseFormat

    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que obtiene el token de un usuario
     * @param in_email [String]]: Email del usuario
     * @param in_password [String]: Contraseña del usuario
     * @return [JWT_KEY]: Token del usuario.
     */

    @GET(getJWTKeyEndpoint)
    suspend fun getJWTKey(@Query("in_email") in_email : String, @Query("in_password") in_password : String): JWT_KEY

    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que agrega una orden a la base de datos
     * @param order [Order]: Orden a agregar
     * @param user_token [String] token del usuario
     * @return [ResponseFormat]: Respuesta del servidor.
     */
    @POST(addOrderwithTokenEndpoint)
    suspend fun addOrderWithToken(@Body order: Order, @Query("user_token") user_token : String): ResponseFormat

    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que agrega una dirección a la base de datos
     * @param address [Direccion]: Dirección a agregar
     * @param user_token [String]: Token del usuario
     */
    @POST(addAddressWithTokenEndpoint)
    suspend fun addAddressWithToken(@Body address: Direccion, @Query("user_token") user_token : String): ResponseFormat

    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que obtiene los datos de un usuario
     * @param user_token [String]: Token del usuario
     * @return [DataUsuario]: Datos del usuario.
     */
    @GET(getUserDataWithTokenEndpoint)
    suspend fun getUserDataWithToken(@Query("user_token") user_token : String) : DataUsuario
    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que obtiene el token de recuperación de contraseña
     * @param in_correo_cliente [String]: Correo del usuario
     * @return [ResponseFormat]: Respuesta del servidor.
     */
    @GET(getRecoveryPasswordTokenEndpoint)
    suspend fun getRecoveryPasswordToken(@Query("in_correo_cliente") in_correo_cliente : String): ResponseFormat

    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que hace la solicitud de cambiar la contraseña
     * @param change [ChangePassword]: Contraseña a cambiar
     * @return [ResponseFormat]: Respuesta del servidor.
     */
    @POST(changePasswordEndpoint)
    suspend fun changePassword(@Body change: ChangePassword ): ResponseFormat

    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que obtiene la información de una orden
     * @param user_token [String]: Token del usuario
     * @return [Orders]: Información de la orden.
     */
    @GET(getOrderInfoWithTokenEndpoint)
    suspend fun getOrderInfoWithToken(@Query("user_token") user_token : String): Orders
    @Headers(
        "User-Agent:Retrofit"
    )
    /**
     * Función que actualiza una dirección de un usuario
     * @param address [Direccion]: Dirección a actualizar
     * @param user_token [String]: Token del usuario
     * @return [ResponseFormat]: Respuesta del servidor.
     */
    @POST(updateAddressWithTokenEndpoint)
    suspend fun updateAddressWithToken(@Body address: Direccion, @Query("user_token") user_token : String): ResponseFormat

}