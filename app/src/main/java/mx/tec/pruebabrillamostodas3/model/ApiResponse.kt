package mx.tec.pruebabrillamostodas3.model


data class ApiResponse(
    val items: List<Item>,
    val hasMore: Boolean,
    val limit: Int,
    val offset: Int,
    val count: Int,
    val links: List<Link2>
)

data class Item(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val cantidad: Int,
    val precio_normal: Int,
    val precio_rebajado: Int
)
data class Link2(
    val rel: String,
    val href: String
)
