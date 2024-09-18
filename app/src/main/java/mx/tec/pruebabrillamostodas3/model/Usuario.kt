package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    val nombre: String,
    val ap_paterno: String,
    val ap_materno: String,
    val fecha_nacimiento: String,
    val email: String,
    val password: String,
    val terminos : Int,
    val publicidad : Int
)
