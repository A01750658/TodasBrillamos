package mx.tec.pruebabrillamostodas3.viewmodel

data class EstadoProducto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio_normal: Int,
    val precio_rebajado: Int,
    val cantidad: Int,
    val rebaja: Int,
    val imagen: ByteArray
)
