package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable
/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API

Formato JSON
{
    "data_info":"17,18;4,5",
    "id_usuario":-1
}
 */
@Serializable
data class Order(
    val data_info : String,
    val id_usuario : Int
)
