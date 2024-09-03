package mx.tec.pruebabrillamostodas3.model

import retrofit2.Call
import retrofit2.http.GET


interface TodasBrillamosAPI
{
    @GET("get_productos/:id")
    fun getProducto(): ApiResponse

    @GET("get_productos/")
    fun getProductos(): ListaProducto
}