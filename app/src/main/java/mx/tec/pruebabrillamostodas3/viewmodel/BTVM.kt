package mx.tec.pruebabrillamostodas3.viewmodel

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.tec.pruebabrillamostodas3.model.Direccion
import mx.tec.pruebabrillamostodas3.model.ModelConnectionR
import mx.tec.pruebabrillamostodas3.model.Order
import mx.tec.pruebabrillamostodas3.model.Producto
import mx.tec.pruebabrillamostodas3.model.Usuario


class BTVM: ViewModel() {
    val modeloR: ModelConnectionR = ModelConnectionR()


    //EstadoDatePicker
    private val _showDatePicker = MutableLiveData(false)
    val showDatePicker: LiveData<Boolean> = _showDatePicker

    fun setShowDatePicker(show: Boolean) {
        _showDatePicker.value = show
    }

    private val _once = MutableStateFlow(false)
    val once: StateFlow<Boolean> = _once
    fun setOnce(show: Boolean) {
        _once.value = show
    }

    //EstadoDatePicker
    private val _contraseñaPerdida = MutableLiveData(false)
    val contraseñaPerdida: LiveData<Boolean> = _contraseñaPerdida

    //Estado Lista Productos proveniente de modelo
    private val _estadoListaProductosModelo = MutableStateFlow(listOf<Producto>())
    val estadoListaProductosModelo: StateFlow<List<Producto>> = _estadoListaProductosModelo
    //Estado cantidad de Productos que se van a recibir del modelo
    private val _estadoCantidadProductosModelo = MutableStateFlow(0)
    val estadoCantidadProductosModelo: StateFlow<Int> = _estadoCantidadProductosModelo


    //Estado Lista Productos que se van a mostrar en la vista
    private val _estadoListaProducto = MutableStateFlow(mutableListOf<EstadoProducto>())
    val estadoListaProducto: StateFlow<MutableList<EstadoProducto>> = _estadoListaProducto


    private val _estadoCarrito = MutableStateFlow<Carrito>(Carrito())
    val estadoCarrito: StateFlow<Carrito> = _estadoCarrito

    private val _estadoañadirCarrito = MutableStateFlow<Pair<Int,Int>>(Pair(0,1))
    val estadoAñadirCarrito: StateFlow<Pair<Int,Int>> = _estadoañadirCarrito

    //Estado Producto Seleccionado
    private val _estadoSeleccionado = MutableStateFlow(-1)
    val estadoSeleccionado: StateFlow<Int> = _estadoSeleccionado

    //Estado Expanded
    private val _estadoExpanded = MutableStateFlow(false)
    val estadoExpanded: StateFlow<Boolean> = _estadoExpanded


    //Estado usuario
    private val _estadoUsuario = MutableStateFlow<EstadoUsuario>(EstadoUsuario())
    val estadoUsuario: StateFlow<EstadoUsuario> = _estadoUsuario

    //Estado errores
    private val _estadoErrors = MutableStateFlow<EstadoErrors>(EstadoErrors())
    val estadoErrors: StateFlow<EstadoErrors> = _estadoErrors

    private val _estadoCopiaDireccion = MutableStateFlow<Direccion>(Direccion())
    val estadoCopiaDireccion: StateFlow<Direccion> = _estadoCopiaDireccion
    var copiaListaProductos = mutableListOf<EstadoProducto>()

    //Estado categorias
    private val _estadoCategorias : MutableStateFlow<MutableSet<String>> = MutableStateFlow(mutableSetOf<String>())
    val estadoCategorias : StateFlow<MutableSet<String>> = _estadoCategorias

    //Estado categoria seleccionada
    private val _categoriaSeleccionada = MutableStateFlow("Todas")
    val categoriaSeleccionada: StateFlow<String> = _categoriaSeleccionada

    //Estado de la lista filtrada por categoría
    var listaFiltradaPorCategoria = mutableListOf<EstadoProducto>()
    fun getProductos() {
        viewModelScope.launch {
            if (_estadoListaProducto.value.isEmpty()) { // Verificar si la lista está vacía
                try {
                    val (cantidad, productos) = modeloR.getProductsWithToken(estadoUsuario.value.key)
                    _estadoCantidadProductosModelo.value = cantidad
                    _estadoListaProductosModelo.value = productos
                    val nuevaLista = mutableListOf<EstadoProducto>() // Crear una nueva lista
                    for (producto in estadoListaProductosModelo.value) {
                        nuevaLista.add(
                            EstadoProducto(
                                id = producto.id,
                                nombre = producto.nombre,
                                descripcion = producto.descripcion,
                                precio_normal = producto.precio_normal,
                                precio_rebajado = producto.precio_rebajado,
                                rebaja = producto.rebaja,
                                categoria = producto.categoria,
                                imagen = modeloR.getProductImage(producto.id),
                                cantidad= producto.cantidad
                            )
                        )
                        _estadoCategorias.value.add(producto.categoria)
                    }
                    _estadoListaProducto.value = nuevaLista // Emitir la nueva lista
                    copiaListaProductos = nuevaLista
                } catch (e: Exception) {
                    println(e)
                }
            }
        }

    }
    //Función para filtrar productos por categoría
    fun setListaFiltradaPorCategoria(categoria: String) {
        listaFiltradaPorCategoria.clear() //limpia lista
        for (producto in copiaListaProductos) {
            if (producto.categoria == categoria) {
                listaFiltradaPorCategoria.add(producto)
            }
        }
        _estadoListaProducto.value = listaFiltradaPorCategoria
    }

    fun setCategoriaSeleccionada(categoria: String) {
        _categoriaSeleccionada.value = categoria
    }

    //Función para resetear la lista filtrada por categoría
    fun resetListaFiltradaPorCategoria() {
        _estadoListaProducto.value = copiaListaProductos
        listaFiltradaPorCategoria.clear()
    }

    fun setEstadoSeleccionado(IdProd: Int) {
        _estadoSeleccionado.value = IdProd
    }

    fun getSelectedProduct() : EstadoProducto{
        estadoListaProducto.value.forEach { estadoProducto ->
            if (estadoProducto.id == estadoSeleccionado.value) {
                return estadoProducto
            }
        }
        return EstadoProducto()
    }


    fun addAddress(direccion: Direccion) {
        viewModelScope.launch {
            try {
                modeloR.addAddress(estadoUsuario.value.key,  direccion)
            } catch (e: Exception) {
                println(e)

            }
        }
    }
    fun updateAddress(direccion: Direccion) {
        viewModelScope.launch {
            try {
                modeloR.updateAddress(estadoUsuario.value.key,  direccion)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun addProducto(index : Int, cantidad: Int) {
        estadoCarrito.value.productos.add(Pair(index, cantidad))
        estadoListaProducto.value[index] = estadoListaProducto.value[index].copy(cantidad = estadoListaProducto.value[index].cantidad - cantidad )
    }

    fun removeProducto(index: Int, cantidad: Int) {
        val producto = estadoListaProducto.value[index].id
        estadoCarrito.value.productos.remove(Pair(producto, cantidad))
    }


    fun addOrder(id: Int) {
        viewModelScope.launch {
            try {
                var orden: Order = Order(modeloR.createDataInfo(estadoCarrito.value.productos), estadoUsuario.value.id)
                val response = modeloR.addOrderWithToken(orden, estadoUsuario.value.key)
            }
            catch (e: Exception) {
                println(e)
            }
        }
    }

    fun signUp(nombre: String, apellido_paterno: String, apellido_materno: String, fecha_nacimiento: String, correo: String, password: String,terminos :Boolean, publicidad : Boolean,telefono: String) {
        val terminos : Int = if (terminos) 1 else 0
        val publicidad : Int = if (publicidad) 1 else 0
        println(terminos)
        println(publicidad)
        val user : Usuario = Usuario(
            nombre,
            apellido_paterno,
            apellido_materno,
            fecha_nacimiento,
            correo,
            //modeloR.hash(password),
            password,
            terminos,
            publicidad,
            telefono
        )
        viewModelScope.launch {
            try {
                val response = modeloR.signUp(user)
                if (response.result=="error"){
                    throw Exception("Could not create User")
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun login(email : String, password: String){
        viewModelScope.launch {
            try {
                val response = modeloR.getJWTKey(email, password)

                if (response.message != "Success generating token") {
                    throw Exception(response.message)
                }
                val userData = modeloR.getUserData(response.data)

                setUserKey(response.data)
                setUserId(response.id)
                setNombreUsuario(userData.nombre)
                setApellidoMaternoUsuario(userData.apellido_materno)
                setApellidoPaternoUsuario(userData.apellido_paterno)
                setDireccionUsuario(userData.direccion)
                setTelefonoUsuario(userData.telefono)
                getProductos()

            } catch (e: Exception) {
                _estadoErrors.value = _estadoErrors.value.copy(errorLogin = true)
            }
        }
    }
    fun changeAddress(){
        _estadoUsuario.value = _estadoUsuario.value.copy(direccion = _estadoCopiaDireccion.value)
    }

    fun openWebPage(url: String, context: Context, startActivity: (Intent) -> Unit) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Manejar la excepción, por ejemplo, mostrando un mensaje al usuario
        }
    }



    fun enviarCorreo(correo:String,context:Context) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$correo")
        }
        context.startActivity(intent)
    }

    fun llamada(telefono: String, context: Context) {
        val intent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:$telefono"))
        context.startActivity(intent)
    }

    fun ubicacion(ubicacion: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$ubicacion"))
        context.startActivity(intent)
    }
    fun setCorreoUsuario(correo: String) {
        _estadoUsuario.value = _estadoUsuario.value.copy(correo = correo)
    }
    fun setContrasenaUsuario(contrasena: String) {
        _estadoUsuario.value = _estadoUsuario.value.copy(password = contrasena)
    }

    fun setNombreUsuario(nombre: String) {
        _estadoUsuario.value = _estadoUsuario.value.copy(nombre = nombre)
    }
    fun setApellidoPaternoUsuario(apellido: String) {
        _estadoUsuario.value = _estadoUsuario.value.copy(apellido_paterno = apellido)
    }
    fun setApellidoMaternoUsuario(apellido: String) {
        _estadoUsuario.value = _estadoUsuario.value.copy(apellido_materno = apellido)
    }
    fun setDireccionUsuario(direccion : Direccion){
        _estadoUsuario.value = _estadoUsuario.value.copy(direccion = direccion)
    }
    fun setConfirmacionContrasenaUsuario(contrasena: String) {
        _estadoUsuario.value = _estadoUsuario.value.copy(confirmacion_password = contrasena)
    }
    fun checkPasswordErrors(){
        _estadoErrors.value = _estadoErrors.value.copy(errorContrasenas=_estadoUsuario.value.password!=_estadoUsuario.value.confirmacion_password)
    }
    fun setUserKey(key : String){
        _estadoUsuario.value = _estadoUsuario.value.copy(key = key)
    }
    fun setUserId(id : Int){
        _estadoUsuario.value = _estadoUsuario.value.copy(id = id)
    }
    fun setErrorLogin(error: Boolean){
        _estadoErrors.value = _estadoErrors.value.copy(errorLogin = error)
    }
    fun setLoading(loading: Boolean){
        _estadoUsuario.value = _estadoUsuario.value.copy(loading = loading)
    }

    fun setTelefonoUsuario(telefono: String) {
        _estadoUsuario.value = _estadoUsuario.value.copy(telefono = telefono)
    }

    fun setIntent(intent: Boolean) {
        _estadoUsuario.value = _estadoUsuario.value.copy(intent = intent)
    }

    fun setFecha(day: Int, month: Int, year: Int) {
        _estadoUsuario.value = _estadoUsuario.value.copy(año_nacimiento = year, mes_nacimiento = month, día_nacimiento = day)
    }

    fun getFecha(): Triple<Int, Int, Int> {
        return Triple(_estadoUsuario.value.año_nacimiento, _estadoUsuario.value.mes_nacimiento, _estadoUsuario.value.día_nacimiento)
    }

    fun setErrorType(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorType = b)
    }

    fun setErrorCell(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorCell = b)
    }

    fun setErrorCorreo(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorCorreo = b)
    }

    fun setErrorLengthPassword(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorLengthPassword = b)
    }

    fun copiarDireccion() {
        _estadoCopiaDireccion.value = _estadoUsuario.value.direccion
        println(_estadoCopiaDireccion.value.id_usuario)
    }
    fun setCalle(calle: String) {
        _estadoCopiaDireccion.value = _estadoCopiaDireccion.value.copy(calle = calle)
    }
    fun setColonia(colonia: String) {
        _estadoCopiaDireccion.value = _estadoCopiaDireccion.value.copy(colonia = colonia)
    }
    fun setMunicipio(municipio: String) {
        _estadoCopiaDireccion.value = _estadoCopiaDireccion.value.copy(municipio = municipio)
    }
    fun setEstado(estado:String) {
        _estadoCopiaDireccion.value = _estadoCopiaDireccion.value.copy(estado = estado)
    }
    fun setCp(cp: String) {
        _estadoCopiaDireccion.value = _estadoCopiaDireccion.value.copy(cp = cp)
    }
    fun setNumeroExt(num:String){
        _estadoCopiaDireccion.value = _estadoCopiaDireccion.value.copy(numero_exterior = num)
    }
    fun setNumeroInt(num:String){
        _estadoCopiaDireccion.value = _estadoCopiaDireccion.value.copy(numero_int = num)
    }
    fun setExpanded(expanded: Boolean) {
        _estadoExpanded.value = expanded
    }

    fun getEstadoUsuario() : EstadoUsuario{
        return _estadoUsuario.value
    }

    fun setCodigoUsuario(codigo: Int){
        _estadoUsuario.value = _estadoUsuario.value.copy(codigo = codigo)
    }

    fun setEstadoAñadirCarrito(producto: Int){
        if (_estadoañadirCarrito.value.first != producto){
            _estadoañadirCarrito.value = Pair(producto,1)
        }
    }
    fun sumarorestarproducto(sign: Int, producto: Int){
        if (sign == 1){
            if(_estadoañadirCarrito.value.second == estadoListaProducto.value[producto].cantidad){
                return
            }
            _estadoañadirCarrito.value = _estadoañadirCarrito.value.copy(first = producto, second = _estadoañadirCarrito.value.second+1)
        }
        else{
            if (_estadoañadirCarrito.value.second == 1){
                return
            }
            _estadoañadirCarrito.value = _estadoañadirCarrito.value.copy(first = producto,second = _estadoañadirCarrito.value.second-1)
        }
    }

    fun setContraseñaPerdida(b: Boolean) {
        _contraseñaPerdida.value = b
    }

    fun recuperarContrasena(email: String){
        viewModelScope.launch {
            try {
                val response = modeloR.getRecoveryPasswordToken(email)
                if (response.result == "error") {
                    throw Exception("Could not recover password")
                }
                println(response.message)
                //cambiar estado loading a false
                _estadoUsuario.value = _estadoUsuario.value.copy(loading = false)
                //cambiar estado contraseña perdida a true
                _contraseñaPerdida.value = true
            }
            catch (e: Exception) {
                println(e)
                _estadoErrors.value = _estadoErrors.value.copy(errorLogin = true)
            }
        }
    }

    fun changePassword(code: Int, email: String, password: String){
        viewModelScope.launch {
            try {
                val response = modeloR.changePassword(code, email, password)
                if (response.result == "error") {
                    throw Exception("Could not change password")
                }
                _estadoUsuario.value = _estadoUsuario.value.copy(loading = false)
            }
            catch (e: Exception) {
                println(e)
                _estadoErrors.value = _estadoErrors.value.copy(errorLogin = true)//cambio de contraseña
            }
        }
    }

    fun setErrorCalle(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorCalle = b)
    }
    fun setErrorMunicipio(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorMunicipio = b)
    }
    fun setErrorColonia(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorColonia = b)
    }
    fun setErrorCp(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorCp = b)
    }
    fun setErrorNumeroExt(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorNumeroExt = b)
    }
    fun setErrorNumeroInt(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorNumeroInt = b)
    }
    fun setErrorEstado(b: Boolean) {
        _estadoErrors.value = _estadoErrors.value.copy(errorEstado = b)
    }

}