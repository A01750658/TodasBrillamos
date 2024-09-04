package mx.tec.pruebabrillamostodas3.model

import retrofit2.Call
import retrofit2.http.GET


interface TodasBrillamosAPI
{
    @GET("get_productos/")
    suspend fun getProductos(): ListaProducto
}