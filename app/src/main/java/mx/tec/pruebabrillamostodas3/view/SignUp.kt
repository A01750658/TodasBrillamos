package mx.tec.pruebabrillamostodas3.view

import android.annotation.SuppressLint
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
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUp(btVM: BTVM, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val showDatePicker by btVM.showDatePicker.observeAsState(false)
    val estado = btVM.estadoUsuario.collectAsState()
    var valorCorreo by rememberSaveable { mutableStateOf(estado.value.correo) }
    var valorPassword by rememberSaveable { mutableStateOf(estado.value.password) }
    var valorNombre by rememberSaveable { mutableStateOf(estado.value.nombre) }
    var valorApellidoPaterno by rememberSaveable { mutableStateOf(estado.value.apellido_paterno) }
    var valorApellidoMaterno by rememberSaveable { mutableStateOf(estado.value.apellido_materno) }
    var valorConfirmacionPassword by rememberSaveable { mutableStateOf(estado.value.confirmacion_password) }
    val estadoErrors = btVM.estadoErrors.collectAsState()

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
            Titulo(titulo ="SIGN UP", modifier = Modifier.padding(bottom = 2.dp), color = MaterialTheme.colorScheme.onTertiary)
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
            Etiqueta("Nombre*", Modifier.padding(bottom = 3.dp))
            Inputtexto(estado.value.nombre,{
                nuevoTexto ->
                valorNombre = nuevoTexto.toString()
                btVM.setNombreUsuario(valorNombre)
            })
            Etiqueta("Apellido Paterno*", Modifier.padding(bottom = 3.dp))
            Inputtexto(estado.value.apellido_paterno,
                {
                    nuevoTexto ->
                    valorApellidoPaterno = nuevoTexto.toString()
                    btVM.setApellidoPaternoUsuario(valorApellidoPaterno)
                })
            Etiqueta("Apellido Materno*", Modifier.padding(bottom = 3.dp))
            Inputtexto(estado.value.apellido_materno,
                {
                    nuevoTexto ->
                    valorApellidoMaterno = nuevoTexto.toString()
                    btVM.setApellidoMaternoUsuario(valorApellidoMaterno)
                })
            Etiqueta("Fecha de Nacimiento", Modifier.padding(bottom = 3.dp))
            //DatePicker(state = DatePickerState(locale = CalendarLocale.GERMAN))

            TextButton(onClick = { btVM.setShowDatePicker(true) },
                Modifier.padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
            ){
                Text(
                    text = "Seleccionar Fecha",
                    textAlign = TextAlign.Center,

                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }

            if (showDatePicker) {
                Dialog(onDismissRequest = { btVM.setShowDatePicker(false) }) {
                    DatePickerScreen(
                        modifier = Modifier
                            .padding(bottom = 3.dp, start = 16.dp)
                            .background(MaterialTheme.colorScheme.onTertiary)
                    )
                }
            }

            //DatePickerScreen(modifier = Modifier.padding(bottom = 3.dp,start = 16.dp).background(MaterialTheme.colorScheme.onTertiary))

            Etiqueta("Correo Electrónico*", Modifier.padding(bottom = 3.dp))
            Inputtexto(estado.value.correo,
                {
                    nuevoTexto ->
                    valorCorreo = nuevoTexto.toString()
                    btVM.setCorreoUsuario(valorCorreo)
                })
            Etiqueta("Contraseña*", Modifier.padding(bottom = 3.dp))
            InputContraseña(estado.value.password,
                { nuevoTexto ->
                    valorPassword = nuevoTexto.toString()
                    btVM.setContrasenaUsuario(valorPassword)
                    btVM.checkPasswordErrors()})
            Etiqueta("Confirmar Contraseña*", Modifier.padding(bottom = 3.dp))
            InputContraseña(estado.value.confirmacion_password,
                { nuevoTexto ->
                    valorPassword = nuevoTexto.toString()
                    btVM.setConfirmacionContrasenaUsuario(valorPassword)
                    btVM.checkPasswordErrors()})
            if (estadoErrors.value.errorContraseñas){
                Text("Las contraseñas no coinciden", Modifier.padding(bottom = 3.dp).fillMaxWidth(), color = MaterialTheme.colorScheme.onPrimary, fontSize = 16.sp,style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
            }

            PreguntaBoton("¿Ya tienes cuenta?","Inicia sesión", {navController.navigate(Pantallas.RUTA_LOGIN)})
            TextButton(onClick = {navController.navigate(Pantallas.RUTA_LOGIN)}){
                Text(
                    text = "Registrarse",
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