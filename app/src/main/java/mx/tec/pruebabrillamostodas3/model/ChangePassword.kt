package mx.tec.pruebabrillamostodas3.model

data class ChangePassword(
    val code : Int,
    val correo_cliente :String,
    val password : String
)
