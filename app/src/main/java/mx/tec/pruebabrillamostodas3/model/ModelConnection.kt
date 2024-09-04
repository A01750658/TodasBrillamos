package mx.tec.pruebabrillamostodas3.model

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

class ModelConnection
{
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun addUser(url:String,user:Usuario) : HttpResponse {
        val response : HttpResponse = client.post(url){
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        return response
    }

    suspend fun addOrder(url:String, order:Order) : HttpResponse {
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
        val result = id_productos.dropLast(1) + ";" + cantidad_producto.dropLast(1)

        return result
    }

    suspend fun getProductList(url:String) : List<Producto>{
        val response : HttpResponse = client.get(url)
        val producto : ListaProducto = response.body()
        return producto.productos
    }
}