package mx.tec.pruebabrillamostodas3.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface TodasBrillamosAPI
{
    @Headers(
        "User-Agent:Retrofit"
    )
    @GET("get_productos/")
    suspend fun getProductos(): ListaProducto
}