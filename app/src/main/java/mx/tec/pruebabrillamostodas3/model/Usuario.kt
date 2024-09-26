package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable

/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API

 * Formato del JSON
    {
        "nombre": "Juan",
        "ap_paterno": "Pérez",
        "ap_materno": "García",
        "fecha_nacimiento": "1990-05-15",
        "email": "juan.perez@example.com",
        "password": "securepassword123",
        "terminos": 1,
        "publicidad": 0,
        "telefono": "555-1234-567"
    }

 */

@Serializable
data class Usuario(
    val nombre: String,
    val ap_paterno: String,
    val ap_materno: String,
    val fecha_nacimiento: String,
    val email: String,
    val password: String,
    val terminos : Int,
    val publicidad : Int,
    val telefono : String
)
