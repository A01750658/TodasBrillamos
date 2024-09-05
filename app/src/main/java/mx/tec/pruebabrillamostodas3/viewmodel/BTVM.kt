package mx.tec.pruebabrillamostodas3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.tec.pruebabrillamostodas3.model.ListaProducto
import mx.tec.pruebabrillamostodas3.model.ModelConnection
import mx.tec.pruebabrillamostodas3.model.Producto


class BTVM: ViewModel() {
    val modelo: ModelConnection = ModelConnection()
    val link: String = "https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/get_productos/"
    private val _estadoLista = MutableStateFlow(listOf<Producto>())
    val estadoLista: StateFlow<List<Producto>> = _estadoLista
    private val _estadoProducto2 = MutableStateFlow<EstadoProducto>(EstadoProducto())
    val estadoProducto2: StateFlow<EstadoProducto> = _estadoProducto2
    private val _estadoListaProducto = MutableStateFlow(mutableListOf<EstadoProducto>())
    val estadoListaProducto: StateFlow<MutableList<EstadoProducto>> = _estadoListaProducto
    val estadoListaProducto2: MutableList<EstadoProducto> = mutableListOf<EstadoProducto>()
    fun getProductos(){
        viewModelScope.launch{
            _estadoLista.value = modelo.getProductList(link)
        }

    }

    fun getImagenProd(_estadoLista: List<Producto>){
        viewModelScope.launch{
            for (producto in estadoLista.value) {

                _estadoProducto2.value = _estadoProducto2.value.copy(id = producto.id, nombre = producto.nombre, descripcion = producto.descripcion, precio_normal = producto.precio_normal, precio_rebajado = producto.precio_rebajado, rebaja = producto.rebaja, imagen = modelo.getProductImage(producto.id))
                estadoListaProducto.value.add(_estadoProducto2.value)
            }
        }
    }


}