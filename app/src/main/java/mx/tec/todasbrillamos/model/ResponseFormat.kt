package mx.tec.todasbrillamos.model
/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API

 * Formato del JSON
{
    "result": "unsuccessful",
    "message": "Error"
}
 */
data class ResponseFormat(
    val result :String,
    val message : String
)
