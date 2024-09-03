package mx.tec.pruebabrillamostodas3.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Call
import retrofit2.awaitResponse
import java.util.concurrent.TimeUnit
import retrofit2.awaitResponse

class ConnectorAPI {
    private val BASE_URL = "https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/"

    private val client = OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_1_1)) // Force HTTP/1.1
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicio by lazy {
        retrofit.create(TodasBrillamosAPI::class.java)
    }

    suspend fun getProducto(): ApiResponse {
        return servicio.getProducto()
    }

    suspend fun getProductos(): ListaProducto {
        return servicio.getProductos()
    }
}

fun main() = runBlocking {
    val connector = ConnectorAPI()
    val producto = connector.getProductos()
    println(producto)
}
