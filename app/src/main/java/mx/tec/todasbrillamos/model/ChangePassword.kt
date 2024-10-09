package mx.tec.todasbrillamos.model

/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
 * Formato JSON
 * {
 *     "code":27220498,
 *     "correo_cliente":"ejemplo@gmail.com",
 *     "password":"0400"
 * }
 */


data class ChangePassword(
    val code : Int,
    val correo_cliente :String,
    val password : String
)
