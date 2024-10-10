package mx.tec.todasbrillamos.viewmodel


/**
 * Clase que representa el estado de los errores en la aplicación.
 * Contiene los atributos necesarios para la visualización de los errores en la aplicación.
 *
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 */

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
    val errorCodigo: Boolean = false,
    val errorUniqueEmail: Boolean = false,
    val errorUniquePhone: Boolean = false,
    val errorSignUp: Boolean = true,
    val errorConexion: Boolean = false,
    val errorFecha: Boolean = false
)
