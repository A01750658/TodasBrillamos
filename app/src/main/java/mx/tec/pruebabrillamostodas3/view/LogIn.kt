package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun LogIn(btVM: BTVM, navController: NavHostController){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        Column{
            Text(
                text = "Pantalla de Login",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(onClick = {navController.navigate(Pantallas.RUTA_APP_HOME)}){
                Text(
                    text = "Acceder",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }


        }
    }
}


