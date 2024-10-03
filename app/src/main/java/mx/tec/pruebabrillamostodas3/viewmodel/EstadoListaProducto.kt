package mx.tec.pruebabrillamostodas3.viewmodel


/**
 * Clase que representa el estado de una lista de producto en la aplicación.
 * Contiene los atributos necesarios para la visualización de una lista de productos (cantidad y objetos).
 *
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 */

data class EstadoListaProducto(
    val cantidad: Int = 0,
    val productos2: List<EstadoProducto> = listOf<EstadoProducto>()
)
