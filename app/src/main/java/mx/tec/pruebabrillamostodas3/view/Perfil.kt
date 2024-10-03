package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.util.copy
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
 * Pantalla de perfil de usuario, que muestra la información personal del usuario como nombre, apellidos,
 * correo, teléfono y dirección de envío. También permite al usuario editar su dirección de envío.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param btVM Viewmodel principal de la aplicación.
 * @param navController Controlador de navegación de la aplicación.
 * @param estado Estado del usuario.
 * @param direccion Dirección de envío de productos.
 * @param estadoUsuario Estado del usuario.
 *
 */

@Composable
fun Perfil(btVM: BTVM, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val estado = btVM.estadoUsuario.collectAsState()
    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation
    // Construcción de la dirección de envío del usuario en base a la información disponible
    var direccion = ""
    println(estado.value.direccion.calle)
    println(estado.value.direccion.numero_int.toInt())
    if (estado.value.direccion.calle != "") {
        if (estado.value.direccion.numero_int == "" || estado.value.direccion.numero_int.toInt() == -1 || estado.value.direccion.numero_int.toInt() == 0){
            direccion="${estado.value.direccion.calle} #${estado.value.direccion.numero_exterior}, ${estado.value.direccion.colonia}, ${estado.value.direccion.municipio}, ${estado.value.direccion.estado}, C.P. ${estado.value.direccion.cp}"
        }
        else{
            direccion="${estado.value.direccion.calle} #${estado.value.direccion.numero_exterior}, Int ${estado.value.direccion.numero_int}, ${estado.value.direccion.colonia}, ${estado.value.direccion.municipio}, ${estado.value.direccion.estado}, C.P. ${estado.value.direccion.cp}"
        }

    }
    else{ direccion = "No hay dirección de envío registrada"}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState)
    ) {
        Column( horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        {
            Icon( // Ícono de usuario
                imageVector = Icons.Default.Person,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
                    .fillMaxWidth(),

            )
            // Título de la pantalla
            Titulo(titulo = "Perfil", color = MaterialTheme.colorScheme.secondaryContainer, fontSize = 50)
            HorizontalDivider(thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 16.dp))
            Spacer(modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
            )

            // Columna que contiene los datos del perfil
            Column(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background((MaterialTheme.colorScheme.primary).copy(alpha = 0.7f))
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                )
                Etiqueta( // Nombre del usuario
                    "Nombre",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary,
                    padding = 30
                )
                UsuarioDisplay(text = estado.value.nombre, fontSize = if( screenOrientation == 1) 22 else 35)
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                )
                Row { // Apellidos
                    Column(modifier = Modifier.weight(1f)) {
                        Etiqueta( // Apellido paterno
                            "Apellido Paterno",
                            modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.onTertiary, padding = 16
                        )
                        UsuarioDisplay(text = estado.value.apellido_paterno, fontSize = if( screenOrientation == 1) 22 else 35)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Etiqueta( // Apellido materno
                            "Apellido Materno",
                            modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.onTertiary, padding = 16
                        )
                        UsuarioDisplay(text = estado.value.apellido_materno, fontSize = if( screenOrientation == 1) 22 else 35)
                    }
                }
                Spacer(modifier = Modifier.padding(6.dp).fillMaxWidth())

                Etiqueta( // Correo electrónico
                    "Correo",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary,
                    padding = 30
                )
                UsuarioDisplay(text = estado.value.correo, fontSize = if( screenOrientation == 1) 22 else 35)

                Spacer(modifier = Modifier.padding(6.dp).fillMaxWidth())

                Etiqueta( // Teléfono
                    "Telefono",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary,
                    padding = 30
                )
                UsuarioDisplay(text = estado.value.telefono, fontSize = if( screenOrientation == 1) 22 else 35)

                Spacer(modifier = Modifier.padding(10.dp).fillMaxWidth())

                Etiqueta( // Dirección de envío
                    "Dirección de envió de productos",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary,
                    padding = 30
                )

                // Muestra la dirección de envío del usuario para editar
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.onTertiary)) {
                    Text( // Texto de la dirección
                        text= direccion,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(vertical = 20.dp)
                            .weight(5f),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        maxLines = 5,
                        fontSize = if (screenOrientation == 1) 15.sp else 25.sp
                    )
                    ElevatedButton( // Botón para editar la dirección
                        onClick = { btVM.copiarDireccion() // Copia la dirección del viewmodel
                            navController.navigate(Pantallas.RUTA_EDITAR_DIRECCION) }, // Navega a la pantalla de edición de dirección
                        modifier = Modifier
                            .padding(top = 50.dp, bottom = 25.dp)
                            .padding(horizontal = 10.dp)
                            .height(50.dp)
                            .width(110.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Text(
                            "Editar",
                            modifier = Modifier.height(20.dp),
                            color = MaterialTheme.colorScheme.onTertiary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp).fillMaxWidth())
        }
    }
}