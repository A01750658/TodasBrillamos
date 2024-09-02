package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable

@Serializable
data class ListaProductos(
    val items: List<Producto>,
    val hasMore: Boolean,
    val limit: Int,
    val offset: Int,
    val count: Int,
    val links: List<Link>
)
