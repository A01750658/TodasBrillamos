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

/**
 * Esta es la pantalla que se muestra una vez que se ha pasado la validación de recuperar contraseña
 * @author Cesar Flores
 * @param btVM Viewmodel principal de la aplicación.
 * @param navController Controlador de navegación de la aplicación.
 * @param modifier modificador
 */
@Composable
fun NuevaContrasena(btVM: BTVM, navController: NavHostController, modifier: Modifier = Modifier){
    val scrollState = rememberScrollState()
    val estado = btVM.estadoUsuario.collectAsState()
    val estadoErrors = btVM.estadoErrors.collectAsState()
    var valorCodigo by rememberSaveable { mutableStateOf(estado.value.codigo) }
    var valorPassword by rememberSaveable { mutableStateOf(estado.value.password) }
    var valorConfirmacionPassword by rememberSaveable { mutableStateOf(estado.value.confirmacion_password) }

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
            Titulo(titulo ="Cambia tu\ncontraseña", modifier = Modifier.padding(bottom = 2.dp), color = MaterialTheme.colorScheme.onTertiary, lineHeight = 45)
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
            Etiqueta("Código de recuperación*", Modifier.padding(bottom = 3.dp))

            InputTexto(estado.value.codigo, onValueChange =
            {
                    nuevoTexto ->
                if (nuevoTexto.contains("\n")){
                    /*TODO*/
                } else {
                    if (nuevoTexto.length != 8 ) {
                        btVM.setErrorCodigo(true)
                        //Error codigo longitud
                        //no existe
                    } else {
                        btVM.setErrorCodigo(false)
                    }
                    valorCodigo = nuevoTexto
                    btVM.setCodigoUsuario(valorCodigo)
                    btVM.setErrorContrasenaPerdida(false)
                }
            },
                keyBoardType = KeyboardType.Number)
            if (estadoErrors.value.errorCodigo) {
                Etiqueta(
                    texto = "El código debe tener 8 dígitos",
                    color = MaterialTheme.colorScheme.inversePrimary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Etiqueta("Contraseña*", Modifier.padding(bottom = 3.dp))
            InputContrasena(estado.value.password,
                { nuevoTexto ->
                    if (nuevoTexto.length < 8) {
                        btVM.setErrorLengthPassword(true)
                    } else{
                        btVM.setErrorLengthPassword(false)
                    }
                    if (nuevoTexto.contains("\n")){
                        /*TODO*/
                    } else {
                        valorPassword = nuevoTexto
                        btVM.setContrasenaUsuario(valorPassword)
                        btVM.checkPasswordErrors()
                    }
                })
            if (estadoErrors.value.errorLengthPassword){
                Etiqueta("La contraseña debe tener al menos 8 caracteres",modifier = Modifier.padding(bottom = 16.dp) , color= MaterialTheme.colorScheme.onPrimary)
            }

            Etiqueta("Confirmar Contraseña*", Modifier.padding(bottom = 3.dp))
            InputContrasena(estado.value.confirmacion_password,
                { nuevoTexto ->
                    btVM.setIntent(false)
                    valorConfirmacionPassword = nuevoTexto
                    btVM.setConfirmacionContrasenaUsuario(valorConfirmacionPassword)
                    btVM.checkPasswordErrors()})
            if (estadoErrors.value.errorContrasenas){
                Etiqueta("Las contraseñas no coinciden", modifier = Modifier.padding(bottom = 16.dp), color = MaterialTheme.colorScheme.error)
            }
            if (estadoErrors.value.errorContrasenaPerdida){
                Etiqueta("Código incorrecto", modifier = Modifier.padding(bottom = 16.dp), color = MaterialTheme.colorScheme.error)
            }

            TextButton(onClick = {
                if (!estadoErrors.value.errorCodigo && !estadoErrors.value.errorContrasenas) {
                    btVM.changePassword(valorCodigo.toInt(), estado.value.correo ,valorPassword)
                    btVM.setLoading(true)
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
                    text = "Click para cambiar",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
            if (estado.value.loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), color = MaterialTheme.colorScheme.tertiary)
            }
            if (btVM.cambioContrasena.value == true) {
                //cambia a la pantalla login
                navController.navigate(Pantallas.RUTA_LOGIN)
                btVM.setCambioContrasena(false)
            }
            Spacer(modifier = Modifier.padding(16.dp))

        }
    }
}