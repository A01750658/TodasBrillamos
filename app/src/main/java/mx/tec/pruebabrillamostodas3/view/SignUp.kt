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
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(btVM: BTVM, navController: NavHostController){
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
            DatePicker(state = DatePickerState(locale = CalendarLocale.GERMAN))
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