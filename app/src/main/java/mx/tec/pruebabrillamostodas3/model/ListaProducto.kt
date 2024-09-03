package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable


@Serializable
data class ListaProducto(
    val productos: List<Producto>
)
