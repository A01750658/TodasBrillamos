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
    val link: String = "https://apex.oracle.com/pls/apex/todasbrillamos/todasbrillamos/get_productos/"

    //EstadoDatePicker
    private val _showDatePicker = MutableLiveData(false)
    val showDatePicker: LiveData<Boolean> = _showDatePicker
    fun setShowDatePicker(show: Boolean) {
        _showDatePicker.value = show
    }

    private val _estadoLista = MutableStateFlow(listOf<Producto>())
    val estadoLista: StateFlow<List<Producto>> = _estadoLista
    fun getProductos(){
        viewModelScope.launch{
            _estadoLista.value = modelo.getProductList(link)
        }

    }
}