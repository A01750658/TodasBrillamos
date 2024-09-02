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

suspend fun getProducts(url : String) : List<Producto>{
    val client = connect()
    val producto : ListaProductos = client.get(url).body()
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

suspend fun addOrder(url:String, order:Order) : HttpResponse{
    val client = connect()
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
    var result = id_productos.dropLast(1) + ";" + cantidad_producto.dropLast(1)

    return result
}

suspend fun main(){
    val products : List<Producto> = getProducts("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/productos")
    for (product in products){
        println(product)
    }
    val user = Usuario("Alan","Vega","Reza","06-MAR-2003","alan25@gmail.com","1234554")
    //println(addOrder("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/add/order"))
    //println(addUser("https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/add/user",user))
    var p : MutableList<Pair<Producto,Int>> = mutableListOf()
   for(product in products){
        p.add(Pair(product,1))
   }
    print(createDataInfo(p))



}