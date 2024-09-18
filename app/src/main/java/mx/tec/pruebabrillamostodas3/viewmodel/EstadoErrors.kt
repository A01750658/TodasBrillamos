package mx.tec.pruebabrillamostodas3.viewmodel

data class EstadoErrors(
    val errorContrasenas: Boolean = false,
    val errorLogin: Boolean = false,
    val errorAvisos: Boolean = false,
    val errorType: Boolean = false,
    val errorCell: Boolean = false,
    val errorCorreo: Boolean = false,
    val errorLengthPassword: Boolean = false
)
