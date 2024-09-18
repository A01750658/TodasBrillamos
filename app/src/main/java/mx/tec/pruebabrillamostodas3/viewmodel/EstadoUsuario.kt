package mx.tec.pruebabrillamostodas3.viewmodel

data class EstadoUsuario(
    val nombre: String = "",
    val apellido_paterno: String = "",
    val apellido_materno: String = "",
    val fecha_nacimiento: String = "",
    val telefono: String = "",
    val correo: String = "",
    val password: String = "",
    val confirmacion_password: String = "",
    val aviso: Boolean = false,
    val marketing: Boolean = false,
    val key : String = "",
    val id : Int = -1,
    val loading: Boolean = false,
    val intent: Boolean = false
)
