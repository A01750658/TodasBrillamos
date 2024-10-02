package mx.tec.pruebabrillamostodas3.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

/**
* @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 *
 * Esta clase tiene las pantallas de la aplicación y sus rutas
 */

sealed class Pantallas (
    val ruta: String,
    val etiqueta: String,
    val icono: ImageVector
){
    companion object{

        var listaPantallas = listOf(Redes, Foros, Home, Tienda, Perfil)
        const val RUTA_APP_HOME = "Home"
        const val RUTA_REDES = "Redes"
        const val RUTA_FOROS = "Foros"
        const val RUTA_TIENDA = "Tienda"
        const val RUTA_PERFIL = "Perfil"
        const val RUTA_LOGIN = "Log In"
        const val RUTA_SIGNUP = "Sign Up"
        const val RUTA_INFO = "Info"
        const val RUTA_CONTACTO = "Contacto"
        const val RUTA_AVISO = "Aviso"
        const val RUTA_CARRITO = "Carrito"
        const val RUTA_EDITAR_DIRECCION = "EditarDireccion"
        const val RUTA_RECUPERARCONTRASEÑA = "RecuperarContraseña"
        const val RUTA_NUEVA_CONTRASEÑA = "NuevaContraseña"
        const val RUTA_CREARFORO = "CrearForo"
        const val RUTA_FORO = "Foro"
        const val RUTA_PAGOS = "Pagos"
        const val RUTA_HISTORIAL = "Historial"
    }

    //Pantallas Menu
    private data object Home: Pantallas(RUTA_APP_HOME, "Zazil", Icons.Default.Home)
    private data object Redes: Pantallas(RUTA_REDES, "Redes", Icons.Default.ThumbUp)
    private data object Foros: Pantallas(RUTA_FOROS, "Foros", Icons.Default.Star)
    private data object Tienda: Pantallas(RUTA_TIENDA, "Catálogo", Icons.Default.ShoppingCart)
    private data object Perfil: Pantallas(RUTA_PERFIL, "Perfil", Icons.Default.AccountBox)

    //Pantallas Independientes
    // (Pantalla de Login y Signup)
    data object LogIn: Pantallas(RUTA_LOGIN, "Log In", Icons.Default.Star)
    data object SignUp: Pantallas(RUTA_SIGNUP, "Sign Up", Icons.Default.Star)
    data object Aviso: Pantallas(RUTA_AVISO, "Aviso", Icons.Default.Star)
    data object RecuperarContraseña: Pantallas(RUTA_RECUPERARCONTRASEÑA, "RecuperarContraseña", Icons.Default.Star)
    data object NuevaContraseña: Pantallas(RUTA_NUEVA_CONTRASEÑA, "NuevaContraseña", Icons.Default.Star)

    //Pantallas que están en Home
    data object Info: Pantallas(RUTA_INFO, "Info", Icons.Default.Star)
    data object Contacto: Pantallas(RUTA_CONTACTO,"Contacto", Icons.Default.Star)

    //Pantallas tienda
    data object BotonProducto: Pantallas(RUTA_CARRITO, "Carrito", Icons.Default.ShoppingCart)
    data object Pagos: Pantallas(RUTA_PAGOS, "Pagos", Icons.Default.Star)
    data object Historial: Pantallas(RUTA_HISTORIAL, "Historial", Icons.Default.Star)

    //Pantallas perfil
    data object EditarDireccion: Pantallas(RUTA_EDITAR_DIRECCION, "EditarDireccion", Icons.Default.Email)

    //Pantallas Foros
    data object CrearForo: Pantallas(RUTA_CREARFORO, "CrearForo", Icons.Default.Email)
    data object Foro: Pantallas(RUTA_FORO, "Foro", Icons.Default.Email)




}