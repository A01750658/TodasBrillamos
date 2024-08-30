package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable


@Serializable
data class Item(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val cantidad: Int,
    val precio_normal: Int,
    val precio_rebajado: Int
)
