package mx.tec.todasbrillamos.viewmodel


/**
 * Clase que representa el estado de un producto en la aplicación.
 * Contiene los atributos necesarios para la visualización de un producto.
 *
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 */

data class EstadoProducto(
    val id: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val precio_normal: Int = 0,
    val precio_rebajado: Int = 0,
    val cantidad: Int = 0,
    val rebaja: Int = 0,
    val categoria :String = "",
    val imagen: ByteArray = ByteArray(0)
)
