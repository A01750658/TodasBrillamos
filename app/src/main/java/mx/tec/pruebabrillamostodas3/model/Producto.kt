package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable


@Serializable
data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio_normal: Int,
    val precio_rebajado: Int,
    val cantidad: Int
)