package mx.tec.pruebabrillamostodas3.model
/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
 * Formato JSON
 *
 * {
 *     "nombre": "UsuarioPrueba1",
 *     "apellido_paterno": "ApPrueba",
 *     "apellido_materno": "ApPrueba2",
 *     "telefono": "0000000000",
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
 * }

 */
data class DataUsuario(
    val nombre: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val telefono : String,
    val direccion : Direccion
)
