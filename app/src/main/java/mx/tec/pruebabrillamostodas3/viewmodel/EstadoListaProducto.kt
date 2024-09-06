package mx.tec.pruebabrillamostodas3.viewmodel

data class EstadoListaProducto(
    val cantidad: Int = 0,
    val productos2: List<EstadoProducto> = listOf<EstadoProducto>()
)
