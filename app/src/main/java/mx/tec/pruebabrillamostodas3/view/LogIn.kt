package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
    val scrollState = rememberScrollState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState),

    ){
        Column(

            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary)
        ){
            Titulo(titulo ="LOG IN", modifier = Modifier.padding(bottom = 8.dp))
            Etiqueta("Correo Electrónico*", Modifier)
            OutlinedTextField(
                value = "email@ejemplo.com",
                onValueChange ={},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary))
            Etiqueta("Contraseña*", Modifier)
            OutlinedTextField(
                value = "Contraseña",
                onValueChange ={},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary))
            Row {
                Text(
                    text = "¿No tienes una cuenta?",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 13.dp)
                        .weight(3f),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(onClick = { navController.navigate(Pantallas.RUTA_SIGNUP) }, modifier = Modifier.weight(2f)) {
                    Text(
                        text = "Regístrate",
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Row {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 13.dp)
                        .weight(3f),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(onClick = { navController.navigate(Pantallas.RUTA_SIGNUP) }, modifier = Modifier.weight(2f)) {
                    Text(
                        text = "Da click aqui",
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            TextButton(onClick = {navController.navigate(Pantallas.RUTA_APP_HOME)}){
                Text(
                    text = "Acceder",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }


        }
    }
}


