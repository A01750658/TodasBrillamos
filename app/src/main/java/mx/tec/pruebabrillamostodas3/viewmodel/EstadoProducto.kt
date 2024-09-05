package mx.tec.pruebabrillamostodas3.viewmodel

data class EstadoProducto(
    val id: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val precio_normal: Int = 0,
    val precio_rebajado: Int = 0,
    val cantidad: Int = 0,
    val rebaja: Int = 0,
    val imagen: ByteArray = ByteArray(0)
)
