package mx.tec.pruebabrillamostodas3.view

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.tec.pruebabrillamostodas3.ui.theme.PruebaBrillamosTodas3Theme
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import mx.tec.pruebabrillamostodas3.viewmodel.PaymentsViewModel
import mx.tec.pruebabrillamostodas3.viewmodel.ValidationsVM

/**
 * @author Alan Vega
 * @author Santiago Chevez
 * Esta es la pantalla principal de la aplicación tiene el topbar y el bottom bar ademas del contenido de la aplicación
 * @param btVM Viewmodel principal de la aplicación.
 * @param paymentsVM Viewmodel de pagos.
 * @param flag si ya se logeo o no.
 * @param savedDeepLinkUri Link
 * @param modifier modificador
 */
@Composable
fun Main(btVM: BTVM, paymentsVM: PaymentsViewModel, flag: Boolean, savedDeepLinkUri: Uri?, validationsVM: ValidationsVM ,modifier: Modifier = Modifier){
    val navController = rememberNavController()
    PruebaBrillamosTodas3Theme{
        Scaffold(topBar = {AppTopBar(navController)},
            bottomBar = {AppBottomBar(navController)}){
                innerPadding ->
            AppNavHost(
                btVM,
                paymentsVM,
                navController,
                flag,
                savedDeepLinkUri,
                validationsVM,
                modifier.padding(innerPadding)
            )
        }
    }
}

/**
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * Función de la top bar y la mestra en las pantallas necesarias
 * @param navController Controlador de navegación de la aplicación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavHostController) {
    if(navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_LOGIN && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_SIGNUP && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_APP_HOME
        && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_PERFIL && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_REDES && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_FOROS
        && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_TIENDA){
        if(navController.currentBackStackEntryAsState().value?.destination?.route == Pantallas.RUTA_CARRITO){
            TopAppBar(
                title = {
                    Text(text = "Continuar comprando",
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onTertiary)
                    }
                }
            )
        }else{
            TopAppBar(
                title = {
                    Text(
                        text = "",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    }
}

/**
 * @author Alan Vega
 * @author Santiago Chevez
 * Esta funcion es el como se ve la bottom bar y la muestra en las pantallas necesarias
 * @param navController Controlador de navegación de la aplicación.
 */
@Composable
fun AppBottomBar(navController: NavHostController) {
    if(navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_LOGIN
        && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_SIGNUP
        && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_AVISO
        && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_RECUPERARCONTRASEÑA){
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onTertiary,

            ){
            val pilaNavegacion by navController.currentBackStackEntryAsState()
            val pantallaActual = pilaNavegacion?.destination

            Pantallas.listaPantallas.forEach{pantalla ->
                NavigationBarItem(
                    selected = pantalla.ruta == pantallaActual?.route,
                    onClick = {
                        navController.navigate(pantalla.ruta){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = {Text(text = pantalla.etiqueta,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 13.sp)},
                    icon = {Icon(
                        imageVector = pantalla.icono,
                        contentDescription = pantalla.etiqueta,
                        tint = MaterialTheme.colorScheme.onTertiary)},
                    alwaysShowLabel = true,
                )
            }
        }
    }
}

/**
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * Esta es la función principal del controlador de la vista de la aplicación
 * Muestra la panatalla requerida
 * @param btVM Viewmodel principal de la aplicación.
 * @param paymentsVM Viewmodel de pagos.
 * @param navController Controlador de navegación de la aplicación.
 * @param flag si ya se logeo o no.
 * @param savedDeepLinkUri Link
 * @param modifier modificador
 */
@Composable
fun AppNavHost(btVM: BTVM, paymentsVM: PaymentsViewModel,navController: NavHostController, flag: Boolean, savedDeepLinkUri: Uri?,validationsVM: ValidationsVM,modifier: Modifier = Modifier) {
    NavHost(navController = navController,
        startDestination = if (!flag) Pantallas.RUTA_LOGIN else Pantallas.RUTA_CARRITO,
        modifier = modifier,){
        composable(Pantallas.RUTA_APP_HOME){
            Home(btVM, navController)
        }
        composable(Pantallas.RUTA_REDES){
            Redes(btVM)
        }
        composable(Pantallas.RUTA_FOROS){
            Foros(navController)
        }
        composable(Pantallas.RUTA_PERFIL){
            Perfil(btVM, navController)
        }
        composable(Pantallas.RUTA_TIENDA){
            Tienda(btVM, modifier, navController)
        }
        composable(Pantallas.RUTA_SIGNUP){
            SignUp(btVM, navController)
        }
        composable(Pantallas.RUTA_LOGIN){
            LogIn(btVM, navController, paymentsVM, validationsVM)
        }
        composable(Pantallas.RUTA_INFO){
            Info(btVM)
        }
        composable(Pantallas.RUTA_CONTACTO){
            Contacto(btVM)
        }
        composable(Pantallas.RUTA_AVISO){
            AvisoyLeyenda()
        }
        composable(Pantallas.RUTA_CARRITO){
            Carrito(btVM, paymentsVM, savedDeepLinkUri)
        }
        composable(Pantallas.RUTA_EDITAR_DIRECCION) {
            EditarDireccion(btVM,navController,validationsVM)
        }
        composable(Pantallas.RUTA_RECUPERARCONTRASEÑA){
            RecuperarContraseña(btVM ,navController)
        }
        composable(Pantallas.RUTA_NUEVA_CONTRASEÑA){
            NuevaContraseña(btVM, navController)
        }
        composable(Pantallas.RUTA_CREARFORO){
            CrearForo(navController){}
        }
        composable(Pantallas.RUTA_FORO+"/{idforo}"){
            TempleteForo(idForo = it.arguments?.getString("idforo")!!)
        }
        composable(Pantallas.RUTA_PAGOS){
            PaymentScreen(btVM,paymentsVM)
        }
    }
}