package mx.tec.pruebabrillamostodas3.model

/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
Formato JSON

{
    "id": 161,
    "pregunta": "EJEMPLO FORO"
},
 * */
data class Foro(

    val id: Int = -1,
    val pregunta :String = ""

)
