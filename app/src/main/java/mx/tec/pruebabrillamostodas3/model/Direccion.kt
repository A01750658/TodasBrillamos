package mx.tec.pruebabrillamostodas3.model

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
