package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val data_info : String,
    val id_usuario : Int
)
