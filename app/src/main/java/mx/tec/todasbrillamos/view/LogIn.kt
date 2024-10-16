package mx.tec.todasbrillamos.view

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import mx.tec.todasbrillamos.viewmodel.BTVM
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mx.tec.todasbrillamos.R
import mx.tec.todasbrillamos.viewmodel.PaymentsViewModel
import mx.tec.todasbrillamos.viewmodel.ValidationsVM

/**
 * Pantalla del inicio de sesión del usuario. Utilza las funciones de elementos de la vista y del viewmodel
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Alan Vega
 * @author Cesar Flores
 * @param btVM Viewmodel principal de la aplicación.
 * @param navController Controlador de navegación de la aplicación.
 * @param paymentsVM Viewmodel de pagos.
 * @param validationsVM Viewmodel de validaciones.
 */
@Composable
fun LogIn(
    btVM: BTVM,
    navController: NavHostController,
    paymentsVM: PaymentsViewModel,
    validationsVM: ValidationsVM
){
    val scrollState = rememberScrollState()
    val estado = btVM.estadoUsuario.collectAsState()
    val estadoErrors = btVM.estadoErrors.collectAsState()
    val estadoRegistroExitoso by btVM.estadoRegistroExitoso.observeAsState(false)
    val estadoCambioContrasena by btVM.cambioContrasena.observeAsState(false)
    var valorCorreo by rememberSaveable { mutableStateOf(estado.value.correo) }
    var valorPassword by rememberSaveable { mutableStateOf(estado.value.password) }
    val estadoHashGuardado by btVM.hashGuardado.observeAsState(false)
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.finalback),
                contentScale = ContentScale.FillBounds
            )
            .verticalScroll(scrollState),

        ){
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE91E63).copy(alpha = 0.6f))
        ){
            Titulo(titulo ="Iniciar sesión", modifier = Modifier.padding(bottom = 2.dp), color = MaterialTheme.colorScheme.onTertiary)
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
            InputTexto(estado.value.correo, onValueChange =
            {
                    nuevoTexto ->
                if (nuevoTexto.contains("\n")){
                    /*TODO*/
                } else {
                    if (validationsVM.validateEmail(nuevoTexto)){
                        btVM.setErrorCorreo(false)
                    } else {
                        btVM.setErrorCorreo(true)
                    }
                    valorCorreo = nuevoTexto
                    btVM.setCorreoUsuario(valorCorreo)
                    btVM.setErrorLogin(false)
                }
            },
                keyBoardType = KeyboardType.Email,
                placeHolder = "usuario@dominio.com"

            )
            if (estadoErrors.value.errorCorreo) {
                Etiqueta(
                    texto = "Debe de ser un correo electrónico",
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            Etiqueta("Contraseña*", Modifier.padding(bottom = 3.dp))
            InputContrasena(estado.value.passwordUnhashed,
                { nuevoTexto ->
                    if (nuevoTexto.contains("\n")){
                        /*TODO*/
                    } else {
                        valorPassword = nuevoTexto
                        btVM.setContrasenaUsuario(valorPassword)
                        btVM.setUnhashedPassword(valorPassword)
                        btVM.setErrorLogin(false)
                    }
                },
                )
            if (estadoErrors.value.errorLogin) {
                Etiqueta( "El correo o la contraseña son incorrectos",
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier =
                    Modifier
                        .padding(bottom = 3.dp)
                    //.background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                btVM.setLoading(false)
                btVM.setHashGuardado(false)
            }
            if (estadoErrors.value.errorConexion){
                Etiqueta("Verifique la conexión a internet e intente de nuevo más tarde.", Modifier.padding(bottom = 16.dp), color= MaterialTheme.colorScheme.onPrimary)
                btVM.setLoading(false)
                btVM.setHashGuardado(false)
            }

            PreguntaBoton("¿No tienes una cuenta?","Regístrate", {btVM.setRegistroExitoso(false); navController.navigate(Pantallas.RUTA_SIGNUP)})
            PreguntaBoton("¿Olvidaste tu contraseña?","Da click aqui" , onClick = {btVM.setRegistroExitoso(false); navController.navigate(Pantallas.RUTA_RECUPERARCONTRASENA) })
            ElevatedButton(onClick = {
                println(estadoHashGuardado)
                if (!estadoErrors.value.errorLogin && !estadoHashGuardado) {
                    btVM.saveHashPassword(context, estado.value.password)
                    println("Haciendo Login")
                    val hashPassword = btVM.getHashPasswordSync(context)
                    btVM.setLoading(true)
                    btVM.login(estado.value.correo, hashPassword.toString())
                    btVM.setRegistroExitoso(false)
                    //val hashPassword = btVM.getHashPasswordSync(context)
                    //btVM.setLoading(true)
                    //btVM.login(estado.value.correo, hashPassword.toString())
                    //btVM.setRegistroExitoso(false)

                    //paymentsVM.saveUserData(context, estado.value.correo, estado.value.password, estado.value.correo, estado.value.key, estado.value.id)
                }
            },
                Modifier
                    .padding(horizontal = 100.dp)
                    .padding(bottom = 16.dp)
                    //.background(MaterialTheme.colorScheme.tertiary)
                ,colors= ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
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

          //  LaunchedEffect(estadoHashGuardado) {
                //if (estadoHashGuardado) {

               // }
          //  }

            if (estadoRegistroExitoso){
                AlertDialog(
                    onDismissRequest = { btVM.setRegistroExitoso(false) },
                    title = { Text("Registro Exitoso") },
                    text = { Text("Su registro se ha realizado con éxito.") },
                    confirmButton = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = androidx.compose.ui.Alignment.Center  // Centra el contenido
                        ) {
                            Button(onClick = { btVM.setRegistroExitoso(false)
                            }){
                                Text("Aceptar", color = MaterialTheme.colorScheme.onTertiary)
                            }
                        }
                    },
                )
            }
            if (estadoCambioContrasena){ //aaaaaaaaaaa
                AlertDialog(
                    onDismissRequest = { btVM.setCambioContrasena(false) },
                    title = { Text("Cambio exitoso") },
                    text = { Text("Su contraseña se ha cambiado con éxito.") },
                    confirmButton = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = androidx.compose.ui.Alignment.Center  // Centra el contenido
                        ) {
                            Button(onClick = { btVM.setCambioContrasena(false)
                            }){
                                Text("Aceptar", color = MaterialTheme.colorScheme.onTertiary)
                            }
                        }
                    },
                )
            }

            if (estado.value.loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), color = MaterialTheme.colorScheme.tertiary)
            }
            if (estado.value.key != "" && estadoHashGuardado){
                btVM.setHashGuardado(false)
                navController.navigate(Pantallas.RUTA_APP_HOME)
            }
            Spacer(modifier = Modifier.padding(16.dp))

        }

    }

}


