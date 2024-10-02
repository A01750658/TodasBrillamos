package mx.tec.pruebabrillamostodas3.viewmodel

import mx.tec.pruebabrillamostodas3.model.Producto

data class Carrito(
    var productos: MutableList<Pair<EstadoProducto,Int>> = mutableListOf<Pair<EstadoProducto,Int>>()
)
