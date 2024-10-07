package mx.tec.todasbrillamos.model

import kotlinx.serialization.Serializable
/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
 * Formato JSON
 *
{
"data": "TOKENEXAMPLE",
"message": "Success generating token",
"id": 222
}

 */
@Serializable
data class JWT_KEY(
    val data: String,
    val message: String,
    val id : Int
)
