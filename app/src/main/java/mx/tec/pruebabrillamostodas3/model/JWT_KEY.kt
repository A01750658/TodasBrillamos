package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable

@Serializable
data class JWT_KEY(
    val data: String,
    val message: String,
    val id : Int
)
