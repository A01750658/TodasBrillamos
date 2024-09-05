package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.tec.pruebabrillamostodas3.ui.theme.PruebaBrillamosTodas3Theme
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
 * @author Alan Vega
 */

@Composable
fun Main(btVM: BTVM, modifier: Modifier = Modifier){
    val navController = rememberNavController()
    PruebaBrillamosTodas3Theme{
        Scaffold(topBar = {AppTopBar(navController)},
            bottomBar = {AppBottomBar(navController)}){
            innerPadding ->
            AppNavHost(
                btVM,
                navController,
                modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavHostController) {
    if(navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_LOGIN && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_SIGNUP && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_APP_HOME
        && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_PERFIL && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_REDES && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_FOROS
        && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_TIENDA){
        TopAppBar(
            title = {
                Text(text = "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth())
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary),
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }){
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back")
                }
            }
        )
    }
}

@Composable
fun AppBottomBar(navController: NavHostController) {
    if(navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_LOGIN && navController.currentBackStackEntryAsState().value?.destination?.route != Pantallas.RUTA_SIGNUP){
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

@Composable
fun AppNavHost(btVM: BTVM, navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController,
        startDestination = Pantallas.RUTA_LOGIN,
        modifier = modifier,){
        composable(Pantallas.RUTA_APP_HOME){
            Home(btVM, navController)
        }
        composable(Pantallas.RUTA_REDES){
            Redes()
        }
        composable(Pantallas.RUTA_FOROS){
            Foros()
        }
        /*
        composable(Pantallas.RUTA_TIENDA){
            Tienda(btVM, modifier)
        }*/
        composable(Pantallas.RUTA_PERFIL){
            Perfil()
        }
        composable(Pantallas.RUTA_SIGNUP){
            SignUp(btVM, navController)
        }
        composable(Pantallas.RUTA_LOGIN){
            LogIn(btVM, navController)
        }
        composable(Pantallas.RUTA_INFO){
            Info()
        }
        composable(Pantallas.RUTA_CONTACTO){
            Contacto()
        }
    }
}
