package mx.tec.todasbrillamos.viewmodel

/**
 * Clase que representa el estado de un producto en el carrito.
 * Contiene una lista mutable de pares, donde cada par contiene producto y cantidad en el carrito.
 *
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param producto Producto que se va a representar.
 * @param cantidad Cantidad de productos que se van a representar.
 * @constructor Crea un EstadoProducto con el producto y la cantidad.
 *
 */

data class Carrito(
    var productos: MutableList<Pair<EstadoProducto,Int>> = mutableListOf<Pair<EstadoProducto,Int>>(),
    var total: Float = 0f
)
