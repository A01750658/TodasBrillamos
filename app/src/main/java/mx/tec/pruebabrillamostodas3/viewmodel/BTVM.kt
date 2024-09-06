package mx.tec.pruebabrillamostodas3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    //EstadoDatePicker
    private val _showDatePicker = MutableLiveData(false)
    val showDatePicker: LiveData<Boolean> = _showDatePicker
    fun setShowDatePicker(show: Boolean) {
        _showDatePicker.value = show
    }
    private val _once =MutableStateFlow(false)
    val once: StateFlow<Boolean> = _once
    fun setOnce(show: Boolean) {
        _once.value = show
    }
    private val _estadoLista = MutableStateFlow(listOf<Producto>())
    val estadoLista: StateFlow<List<Producto>> = _estadoLista
    private val _estadoCantidad = MutableStateFlow(0)
    val estadoCantidad: StateFlow<Int> = _estadoCantidad

    private val _estadoProducto2 = MutableStateFlow<EstadoProducto>(EstadoProducto())
    val estadoProducto2: StateFlow<EstadoProducto> = _estadoProducto2
    private val _estadoListaProducto = MutableStateFlow(mutableListOf<EstadoProducto>())
    val estadoListaProducto: StateFlow<MutableList<EstadoProducto>> = _estadoListaProducto
    val estadoListaProducto2: MutableList<EstadoProducto> = mutableListOf<EstadoProducto>()

    val user = modelo.createUser("Cesar","Dia","Jueves","06-MAR-2003","cesar@gmail.com","1234554")
    fun getProductos(){
        viewModelScope.launch{
            try {
                val (cantidad, productos) = modelo.getProductsWithToken(user)
                _estadoCantidad.value = cantidad
                _estadoLista.value = productos
                getImagenProd(_estadoLista.value)
            }catch (e: Exception){
            println(e)
        }
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