package mx.tec.pruebabrillamostodas3.model

/**
 * @author Carlos Iker Fuentes Reyes
 * @author Santiago Chevez Trejo
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
 * Formato JSON
 *
 *     "direccion": {
 *         "calle": "Calle Prueba ",
 *         "colonia": "Colonia Prueba",
 *         "municipio": "Municipio prueba",
 *         "estado": "ESTADO",
 *         "cp": 00000,
 *         "numero_int": -1,
 *         "numero_exterior": 5,
 *         "id_usuario": 1
 *     }
 */
data class Direccion(
    val calle : String = "",
    val colonia : String = "",
    val municipio : String = "",
    val estado : String ="",
    val cp : String = "",
    val numero_int : String ="",
    val numero_exterior : String ="",
    val id_usuario : Int =0
)
