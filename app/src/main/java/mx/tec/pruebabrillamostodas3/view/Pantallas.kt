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

sealed class Pantallas (
    val ruta: String,
    val etiqueta: String,
    val icono: ImageVector
){
    companion object{
        var listaPantallas = listOf(Home, Redes, Foros, Tienda, Perfil)
        const val RUTA_APP_HOME = "Home"
        const val RUTA_REDES = "Redes"
        const val RUTA_FOROS = "Foros"
        const val RUTA_TIENDA = "Tienda"
        const val RUTA_PERFIL = "Perfil"
        const val RUTA_LOGIN = "Log In"
        const val RUTA_SIGNUP = "Sign Up"
        const val RUTA_CATALOGO = "Catalogo"
        const val RUTA_INFO = "Info"
        const val RUTA_CONTACTO = "Contacto"
    }

    //Pantallas Menu
    private data object Home: Pantallas(RUTA_APP_HOME, "Home", Icons.Default.Home)
    private data object Redes: Pantallas(RUTA_REDES, "Redes", Icons.Default.ThumbUp)
    private data object Foros: Pantallas(RUTA_FOROS, "Foros", Icons.Default.Star)
    private data object Tienda: Pantallas(RUTA_TIENDA, "Tienda", Icons.Default.ShoppingCart)
    private data object Perfil: Pantallas(RUTA_PERFIL, "Perfil", Icons.Default.AccountBox)

    //Pantallas Independientes (Pantalla de Login y Signup)
    data object LogIn: Pantallas(RUTA_LOGIN, "Log In", Icons.Default.Star)
    data object SignUp: Pantallas(RUTA_SIGNUP, "Sign Up", Icons.Default.Star)
    data object Catalogo: Pantallas(RUTA_CATALOGO, "Catalogo", Icons.Default.Star)
    data object Info: Pantallas(RUTA_INFO, "Info", Icons.Default.Star)
    data object Contacto: Pantallas(RUTA_CONTACTO,"Contacto", Icons.Default.Star)


}