package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable


@Serializable
data class Link(
    val rel: String,
    val href: String
)

