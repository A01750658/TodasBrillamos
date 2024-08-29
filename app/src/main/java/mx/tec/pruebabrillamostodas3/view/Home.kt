package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
 * @author Alan Vega
 */

@Composable
fun Home(btVM: BTVM, navController: NavHostController){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    )
    {
      Column(modifier = Modifier){
          Titulo()
          ElevatedButton(onClick = {navController.navigate(Pantallas.RUTA_TIENDA)}, modifier = Modifier.fillMaxSize().weight(1f)){
              Text(
                  text = "Catálogo",
                  textAlign = TextAlign.Center,
                  modifier = Modifier.fillMaxWidth().padding(24.dp)
              )
          }
          ElevatedButton(onClick = {navController.navigate(Pantallas.RUTA_INFO)}, modifier = Modifier.fillMaxSize().weight(1f)){
              Text(
                  text = "Conoce más información",
                  textAlign = TextAlign.Center,
                  modifier = Modifier.fillMaxWidth().padding(24.dp)
              )
          }
          ElevatedButton(onClick = {navController.navigate(Pantallas.RUTA_CONTACTO)}, modifier = Modifier.fillMaxSize().weight(1f)){
              Text(
                  text = "Contáctanos",
                  textAlign = TextAlign.Center,
                  modifier = Modifier.fillMaxWidth().padding(24.dp)
              )
          }
      }
    }
}