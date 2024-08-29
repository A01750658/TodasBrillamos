package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LogIn(btVM: BTVM, navController: NavHostController){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary)
        ){
            Text(
                text = "LOG IN",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 48.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(bottom = 8.dp).fillMaxWidth()
            )
            Text(
                text = "Correo electrónico*",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier =  Modifier
                    .padding(horizontal = 16.dp, vertical = 5.dp)
                    .align(alignment = Alignment.Start)
            )
            OutlinedTextField(
                value = "email@ejemplo.com",
                onValueChange ={},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth().background(MaterialTheme.colorScheme.secondary))

            TextButton(onClick = {navController.navigate(Pantallas.RUTA_APP_HOME)}){
                Text(
                    text = "Acceder",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary
                )
            }


        }
    }
}


