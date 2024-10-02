package mx.tec.pruebabrillamostodas3.viewmodel

data class EstadoErrors(
    val errorContrasenas: Boolean = false,
    val errorLogin: Boolean = false,
    val errorAvisos: Boolean = false,
    val errorType: Boolean = false,
    val errorCell: Boolean = false,
    val errorCorreo: Boolean = false,
    val errorLengthPassword: Boolean = false,
    val errorMessage : String = "",
    val errorCalle: Boolean = false,
    val errorNumeroExt: Boolean = false,
    val errorNumeroInt: Boolean = false,
    val errorColonia: Boolean = false,
    val errorMunicipio: Boolean = false,
    val errorCp: Boolean = false,
    val errorEstado: Boolean = false,
    val errorCodigo: Boolean = false
)
