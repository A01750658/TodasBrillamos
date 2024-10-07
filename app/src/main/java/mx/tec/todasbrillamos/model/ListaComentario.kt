package mx.tec.todasbrillamos.model
/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
Formato JSON
"comentarios": [
    {
    "id_foro": 161,
    "respuesta": "EJEMPLO"
    },
    {
    "id_foro": 161,
    "respuesta": "EJEMPLO2
    }
]
 * */

data class ListaComentario(
    val comentarios: List<Comentario>

)