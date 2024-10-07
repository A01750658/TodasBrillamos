package mx.tec.todasbrillamos.model
/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
Formato JSON
{
"id_foro": 161,
"respuesta": "EJEMPLO"
}
 * */
data class Comentario(
    val id_foro : Int = -1,
    val respuesta : String = ""
)
