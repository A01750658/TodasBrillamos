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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUp(btVM: BTVM, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var selectedDate by remember { mutableStateOf("") }

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
            Inputtexto("",{})
            Etiqueta("Apellido Paterno*", Modifier.padding(bottom = 3.dp))
            Inputtexto("",{})
            Etiqueta("Apellido Materno*", Modifier.padding(bottom = 3.dp))
            Inputtexto("",{})
            Etiqueta("Fecha de Nacimiento", Modifier.padding(bottom = 3.dp))
            //DatePicker(state = DatePickerState(locale = CalendarLocale.GERMAN))

            // Llama a showDatePicker desde una corrutina
            Button(onClick = {
                coroutineScope.launch {
                    showDatePicker(context) { day, month, year ->
                        selectedDate = "$day/${month + 1}/$year"
                    }
                }
            }) {
                Text(text = if (selectedDate.isEmpty()) "Select Date" else selectedDate)
            }

            Etiqueta("Correo Electrónico*", Modifier.padding(bottom = 3.dp))
            Inputtexto("",{})
            Etiqueta("Contraseña*", Modifier.padding(bottom = 3.dp))
            Inputtexto("",{})
            Etiqueta("Confirmar Contraseña*", Modifier.padding(bottom = 3.dp))
            Inputtexto("",{})
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