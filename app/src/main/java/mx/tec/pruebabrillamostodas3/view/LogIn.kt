package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.typesafe.config.ConfigException.Null

/**
 * @author Santiago Chevez
 */
@Composable
fun LogIn(btVM: BTVM, navController: NavHostController){
    val scrollState = rememberScrollState()
    val estado = btVM.estadoUsuario.collectAsState()
    var valorCorreo by rememberSaveable { mutableStateOf(estado.value.correo) }
    var valorPassword by rememberSaveable { mutableStateOf(estado.value.password) }
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
            Titulo(titulo ="LOG IN", modifier = Modifier.padding(bottom = 2.dp), color = MaterialTheme.colorScheme.onTertiary)
            Spacer(modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.onTertiary)
                .padding(bottom = 2.dp)
                .fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )
            Etiqueta("Correo Electrónico*", Modifier.padding(bottom = 3.dp))
            Inputtexto(estado.value.correo, onValueChange =
                {
                    nuevoTexto ->
                    valorCorreo = nuevoTexto.toString()
                    btVM.setCorreoUsuario(valorCorreo)
                })
            Etiqueta("Contraseña*", Modifier.padding(bottom = 3.dp))
            InputContraseña(estado.value.password,
                { nuevoTexto ->
                    valorPassword = nuevoTexto.toString()
                    btVM.setContrasenaUsuario(valorPassword) })
            PreguntaBoton("¿No tienes una cuenta?","Regístrate", {navController.navigate(Pantallas.RUTA_SIGNUP)})
            PreguntaBoton("¿Olvidaste tu contraseña?","Da click aqui" , onClick = { /*TODO*/ })
            TextButton(onClick = {
                btVM.login(btVM.estadoUsuario.value.correo, btVM.estadoUsuario.value.password)
                navController.navigate(Pantallas.RUTA_APP_HOME)},
                Modifier.padding(horizontal = 100.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                    ){
                Text(
                    text = "Acceder",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }


        }
    }
}


