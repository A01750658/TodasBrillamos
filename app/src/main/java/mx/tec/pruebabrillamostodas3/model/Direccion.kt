package mx.tec.pruebabrillamostodas3.model

data class Direccion(
    val calle : String,
    val colonia : String,
    val municipio : String,
    val estado : String,
    val cp : Int,
    val numero_int : Int,
    val numero_ext : Int,
    val user_id : Int
)
