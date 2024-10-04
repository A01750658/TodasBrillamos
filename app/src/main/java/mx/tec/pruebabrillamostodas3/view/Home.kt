package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.foundation.Image
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import mx.tec.pruebabrillamostodas3.R
import mx.tec.pruebabrillamostodas3.viewmodel.PaymentsViewModel

/**
 * Pantalla principal de la aplicación donde se muestra información de la marca y sobre la aplicación
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andres Cabrera
 * @param navController necesario para navegar entre distintas pantallas
 */
@Composable
fun Home(navController: NavHostController,btVM: BTVM,paymentsVM: PaymentsViewModel){
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation
    val estadoLoginExitoso = btVM.estadoLoginExistoso.collectAsState()
    val estado = btVM.estadoUsuario.collectAsState()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState)
    )
    {
      Column(horizontalAlignment =  Alignment.CenterHorizontally,
          modifier = Modifier.padding(horizontal = 16.dp)){
          Image(painter = painterResource(id = R.drawable.log),
              contentDescription = "Logo",
              modifier = Modifier
                  .size(170.dp)
                  .padding(20.dp))
          //Titulo("ZAZIL",color= MaterialTheme.colorScheme.secondaryContainer, fontSize = 90)
          Subtitulo("Cambia el mundo con un solo gesto.", fontSize = if (screenOrientation == 1) 20 else 30)
          HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
          Column(
              Modifier
                  .padding(vertical = 20.dp)
                  .clip(RoundedCornerShape(12.dp))
                  .border(
                      color = MaterialTheme.colorScheme.tertiary,
                      width = 3.dp,
                      shape = MaterialTheme.shapes.medium
                  )
                  .background(color = MaterialTheme.colorScheme.onTertiary)){
              Text(text = "¡Una menstruación sostenible!",
                  textAlign = TextAlign.Center,
                  style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp),
                  color = MaterialTheme.colorScheme.tertiary,
                  modifier = Modifier
                      .padding(10.dp)
                      .fillMaxWidth(),
                  )
              Text(text = "Encuentra los mejores productos de la marca ZAZIL para ti, que son reutilizables" +
                      " e incluso son sonstenibles.\n" +
                      "Tampoco olvides visitar la sección de información para aprender más sobre la menstruación.",
                  textAlign = TextAlign.Center,
                  style = MaterialTheme.typography.bodySmall.copy(fontSize = 18.sp),
                  modifier = Modifier
                      .padding(start = 15.dp, bottom = 10.dp, end = 15.dp)
                      .fillMaxWidth())
          }
          BotonTextandIcon(text = "Ver Catálogo", icon = Icons.Default.ShoppingCart, color=MaterialTheme.colorScheme.tertiary, onClick = {navController.navigate(Pantallas.RUTA_TIENDA){
              popUpTo(navController.graph.findStartDestination().id){
                  saveState = true
              }
              launchSingleTop = true
              restoreState = true
          }
          }, fontSize = if (screenOrientation == 1) 25 else 40)
          BotonTextandIcon(text = " Conócenos",icon = Icons.Default.Info, onClick = {navController.navigate(Pantallas.RUTA_INFO)},color = MaterialTheme.colorScheme.primaryContainer, fontSize = if (screenOrientation == 1) 25 else 40)
          //BotonTextandIcon(text = "Contáctanos", icon = Icons.Default.Person, onClick = { navController.navigate(Pantallas.RUTA_CONTACTO)}, color = MaterialTheme.colorScheme.secondaryContainer,fontSize = if (screenOrientation == 1) 25 else 40)
      }
        if(estadoLoginExitoso.value){
            paymentsVM.saveUserData(context, estado.value.correo, estado.value.password, estado.value.correo, estado.value.key, estado.value.id)
            btVM.setEstadoLogin(false)
        }
    }
}