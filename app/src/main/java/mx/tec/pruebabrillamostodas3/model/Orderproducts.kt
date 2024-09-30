package mx.tec.pruebabrillamostodas3.model

/**
 * @author Carlos Iker Fuentes Reyes
 *  Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
{
    "nombre": "Toalla Femenina Reutilizable Regular Violeta",
    "cantidad": 4,
    "Total": 0
}
 */
data class Orderproducts(
    val nombre: String,
    val cantidad: Int,
    val total : Int
)
