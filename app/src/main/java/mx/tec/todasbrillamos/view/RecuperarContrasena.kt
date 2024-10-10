package mx.tec.todasbrillamos.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mx.tec.todasbrillamos.R
import mx.tec.todasbrillamos.viewmodel.BTVM
import mx.tec.todasbrillamos.viewmodel.ValidationsVM

/**
 * Esta es la pantalla de recuperar contraseña, en donde el usuario puede ingresar su correo electrónico
 * para recibir la recuperación de contraseña. Si el correo es válido, el sistema procede con el proceso de recuperación.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Augusto
 *
 * @param vmodel Viewmodel principal de la aplicación.
 * @param btVM Viewmodel de la pantalla.
 * @param estado Estado de la pantalla.
 * @param estadoErrors Estado de los errores de la pantalla.
 * @param valorCorreo Valor del correo electrónico.
 *
 */

@Composable
fun RecuperarContrasena(btVM: BTVM, navController: NavHostController, modifier: Modifier = Modifier){
    // Estados para manejar la información de usuario y errores del ViewModel
    val scrollState = rememberScrollState()
    val validations = ValidationsVM()
    val estado = btVM.estadoUsuario.collectAsState()
    val estadoErrors = btVM.estadoErrors.collectAsState()
    var valorCorreo by rememberSaveable { mutableStateOf(estado.value.correo) }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.twoback),
                contentScale = ContentScale.FillBounds)
            .verticalScroll(scrollState),

        ){
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE91E63).copy(alpha = 0.6f))
        ){
            //Titulo
            Titulo(titulo ="Recuperar\ncontraseña", modifier = Modifier.padding(bottom = 2.dp), color = MaterialTheme.colorScheme.onTertiary, lineHeight = 45)
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
            // Campo de entrada para el correo electrónico
            Etiqueta("Correo Electrónico*", Modifier.padding(bottom = 3.dp))
            InputTexto(estado.value.correo, onValueChange =
            {
                    nuevoTexto ->
                btVM.setErrorCorreoReg(false)
                if (nuevoTexto.contains("\n")){
                    // No hace nada si contiene un salto de línea
                    /*TODO*/
                } else {
                    // Validación que el formato del correo sea el correcto
                    if (!validations.validateEmail(nuevoTexto)){
                        btVM.setErrorCorreo(true) // Marca error si no contiene '@' o '.'
                    } else {
                        btVM.setErrorCorreo(false) // Elimina el error si es válido
                    }
                    valorCorreo = nuevoTexto
                    btVM.setCorreoUsuario(valorCorreo) // Actualiza el correo en el ViewModel
                    btVM.setErrorLogin(false) // Reinicia el error de inicio de sesión
                }
            },
                keyBoardType = KeyboardType.Email) // Teclado específico para escribir el correo
            // Muestra un mensaje de error si el correo es inválido
            if (estadoErrors.value.errorCorreo) {
                Etiqueta(
                    texto = "Debe de ser un correo electrónico",
                    color = MaterialTheme.colorScheme.inversePrimary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            // Muestra un mensaje de error si el correo no está registrado en la base de datos
            if (estadoErrors.value.errorCorreoReg) {
                Etiqueta( "El correo no está registrado",
                    color = MaterialTheme.colorScheme.inversePrimary,
                    modifier =
                    Modifier
                        .padding(bottom = 3.dp)
                )
                btVM.setLoading(false) // Detiene el estado de carga
            }
            // Botón para solicitar la recuperación de la contraseña

            TextButton(onClick = {
                if (!estadoErrors.value.errorLogin) {
                    btVM.recuperarContrasena(valorCorreo) // Llama al método para recuperar la contraseña
                    btVM.setLoading(true) // Activa el estado de carga
                }
            },
                Modifier
                    .padding(horizontal = 100.dp)
                    .padding(bottom = 16.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
            ){
                Text(
                    text = "Clic para recuperar",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
            // Muestra un indicador de carga si el estado `loading` está activo
            if (estado.value.loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), color = MaterialTheme.colorScheme.tertiary)
            }
            Spacer(modifier = Modifier.padding(16.dp))
            // Comprueba si la contraseña ha sido recuperada
            if (btVM.contrasenaPerdida.value == true) {
                //cambia a la pantalla nueva contraseña
                navController.navigate(Pantallas.RUTA_NUEVA_CONTRASENA)
                btVM.setContrasenaPerdida(false) // Hace un reset de la contraseña perdida en el ViewModel
            }
        }
    }
}