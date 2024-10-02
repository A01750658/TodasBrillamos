package mx.tec.pruebabrillamostodas3.view

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
import mx.tec.pruebabrillamostodas3.R
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

@Composable
fun NuevaContraseña(btVM: BTVM, navController: NavHostController, modifier: Modifier = Modifier){
    val scrollState = rememberScrollState()
    val estado = btVM.estadoUsuario.collectAsState()
    val estadoErrors = btVM.estadoErrors.collectAsState()
    var valorCodigo by rememberSaveable { mutableStateOf(estado.value.codigo) }
    var valorPassword by rememberSaveable { mutableStateOf(estado.value.password) }

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

            InputTexto(estado.value.codigo.toString(), onValueChange =
            {
                    nuevoTexto ->
                if (nuevoTexto.contains("\n")){
                    /*TODO*/
                } else {
                    if (nuevoTexto.length != 8 ) {
                        btVM.setErrorCorreo(true)
                        //Error codigo longitud
                        //no existe
                    } else {
                        btVM.setErrorCorreo(false)
                    }

                    if (nuevoTexto.length > 0){
                        valorCodigo = nuevoTexto.toInt()
                    }
                    else{
                        valorCodigo = 0
                    }

                    btVM.setCodigoUsuario(valorCodigo)
                    btVM.setErrorLogin(false) //error recuperar contraseña
                }
            },
                keyBoardType = KeyboardType.Number)

            Etiqueta("Contraseña*", Modifier.padding(bottom = 3.dp))
            InputContraseña(estado.value.password,
                { nuevoTexto ->
                    if (nuevoTexto.contains("\n")){
                        /*TODO*/
                    } else {
                        valorPassword = nuevoTexto
                        btVM.setContrasenaUsuario(valorPassword)
                    }
                })

            if (estadoErrors.value.errorCorreo) {
                Etiqueta(
                    texto = "Debe de ser un correo electrónico",
                    color = MaterialTheme.colorScheme.inversePrimary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            if (estadoErrors.value.errorLogin) {
                Etiqueta( "El correo no es válido",
                    color = MaterialTheme.colorScheme.inversePrimary,
                    modifier =
                    Modifier
                        .padding(bottom = 3.dp)
                    //.background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                btVM.setLoading(false)
            }
            TextButton(onClick = {
                if (!estadoErrors.value.errorLogin) {
                    btVM.changePassword(valorCodigo, estado.value.correo ,valorPassword)
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
            Spacer(modifier = Modifier.padding(16.dp))

        }
    }
}