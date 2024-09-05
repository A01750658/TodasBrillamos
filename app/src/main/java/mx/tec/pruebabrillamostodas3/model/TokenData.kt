package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenData(
    val user_token: String
)
