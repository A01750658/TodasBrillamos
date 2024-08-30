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


fun connect() : HttpClient{
    val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json()
        }
    }
    return client
}

suspend fun getProducts(url : String) : List<Item>{
    val client = connect()
    val producto : ApiResponse = client.get(url).body()
    return producto.items
}

suspend fun addUser(url:String,user:Usuario) : HttpResponse{
    val client = connect()
    val response : HttpResponse = client.post(url){
        contentType(ContentType.Application.Json)
        setBody(user)
    }
    return response
}

suspend fun addOrder(url:String) : HttpResponse{
    val client = connect()
    val response : HttpResponse = client.post(url){
        contentType(ContentType.Application.Json)
        setBody(Order("15,16,17;1,1,2",3))
    }
    return response
}


suspend fun main(){
    val products : List<Item> = getProducts("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/productos")
    for (product in products){
        println(product)
    }
    val user = Usuario("Alan","Vega","Reza","06-MAR-2003","alan25@gmail.com","1234554")
    println(addOrder("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/add/order"))
    println(addUser("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/add/user",user))



}