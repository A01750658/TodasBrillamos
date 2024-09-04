package mx.tec.pruebabrillamostodas3.model

import io.ktor.client.statement.HttpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface TodasBrillamosAPI
{
    @Headers(
        "User-Agent:Retrofit"
    )
    @GET("get_productos/")
    suspend fun getProductos(): ListaProducto
    @Headers(
        "User-Agent:Retrofit"
    )
    @POST("add/user")
    suspend fun addUser(@Body user: Usuario): HttpResponse
    @Headers(
        "User-Agent:Retrofit"
    )
    @POST("add/order")
    suspend fun addOrder(@Body order: Order): HttpResponse

}