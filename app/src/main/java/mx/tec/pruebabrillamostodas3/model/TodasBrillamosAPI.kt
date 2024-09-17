package mx.tec.pruebabrillamostodas3.model

import io.ktor.client.statement.HttpResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

const val addUserEndpoint = "add/user"
const val addOrderwithTokenEndpoint = "add/order"
const val getJWTKeyEndpoint = "getToken/"
const val getProductImageEndpoint = "product_images/"
const val getProductsWithTokenEndpoint = "productos/"
const val addAddressWithTokenEndpoint = "add/address"


interface TodasBrillamosAPI
{

    @Headers(
        "User-Agent:Retrofit"
    )
    @GET(getProductsWithTokenEndpoint)
    suspend fun getProductos( @Query("user_token") user_token : String): ListaProducto

    @Headers(
        "User-Agent:Retrofit"
    )
    @GET(getProductImageEndpoint)
    suspend fun getProductImage(@Query("image_id") image_id : Int): ByteArray

    @Headers(
        "User-Agent:Retrofit"
    )
    @POST("add/user")
    suspend fun addUser(@Body user: Usuario): ResponseFormat

    @Headers(
        "User-Agent:Retrofit"
    )
    @GET(getJWTKeyEndpoint)
    suspend fun getJWTKey(@Query("in_email") in_email : String, @Query("in_password") in_password : String): JWT_KEY

    @Headers(
        "User-Agent:Retrofit"
    )
    @POST(addOrderwithTokenEndpoint)
    suspend fun addOrderWithToken(@Body order: Order, @Query("user_token") user_token : String): ResponseFormat

    @Headers(
        "User-Agent:Retrofit"
    )
    @POST(addAddressWithTokenEndpoint)
    suspend fun addAddressWithToken(@Body address: Direccion, @Query("user_token") user_token : String): ResponseFormat

}