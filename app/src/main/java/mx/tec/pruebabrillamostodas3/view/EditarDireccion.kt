package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.ktor.websocket.Frame
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

@Composable
fun EditarDireccion(btVM: BTVM, navController: NavHostController){
    val scrollState = rememberScrollState()
    val scrollPosition = scrollState.value
    val maxScrollPosition = scrollState.maxValue
    val estado = btVM.estadoCopiaDireccion.collectAsState()
    val estado_expanded = btVM.estadoExpanded.collectAsState()
    var selectedOptionText by remember { mutableStateOf(estado.value.estado) }
    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation
    val estadosMexico = listOf(
        "Aguascalientes",
        "Baja California",
        "Baja California Sur",
        "Campeche",
        "Chiapas",
        "Chihuahua",
        "Ciudad de México",
        "Coahuila de Zaragoza",
        "Colima",
        "Durango",
        "Guanajuato",
        "Guerrero",
        "Hidalgo",
        "Jalisco",
        "México",
        "Michoacán de Ocampo",
        "Morelos",
        "Nayarit",
        "Nuevo León",
        "Oaxaca",
        "Puebla",
        "Querétaro",
        "Quintana Roo",
        "San Luis Potosí",
        "Sinaloa",
        "Sonora",
        "Tabasco",
        "Tamaulipas",
        "Tlaxcala",
        "Veracruz de Ignacio de la Llave",
        "Yucatán"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
            //.border(5.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
        )
        {
            if (screenOrientation == 1) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(80.dp)
                        .fillMaxWidth(),

                    )
                Titulo(
                    titulo = "Editar Dirección",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontSize = 50
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background((MaterialTheme.colorScheme.primary).copy(alpha = 0.75f))

            ) {
                Spacer(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                )
                Subtitulo(
                    "Direccion",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = 32,
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = MaterialTheme.colorScheme.onTertiary,
                )
                Column (modifier = Modifier.verticalScroll(scrollState)) {

                    Etiqueta("Calle*")
                    InputTexto(estado.value.calle,
                        { nuevoTexto -> btVM.setCalle(nuevoTexto) })
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Etiqueta("Numero Exterior*")
                            InputTexto(
                                estado.value.numero_exterior,
                                { nuevoTexto -> btVM.setNumeroExt(nuevoTexto) },
                                keyBoardType = KeyboardType.Number
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Etiqueta("Numero Interior")
                            InputTexto(
                                estado.value.numero_int,
                                { nuevoTexto -> btVM.setNumeroInt(nuevoTexto) })
                        }
                    }
                    Etiqueta("Colonia*")
                    InputTexto(estado.value.colonia,
                        { nuevoTexto -> btVM.setColonia(nuevoTexto) })

                    Etiqueta("Municipio*")
                    InputTexto(estado.value.municipio,
                        { nuevoTexto -> btVM.setMunicipio(nuevoTexto) })
                    Etiqueta("Codigo Postal*")
                    InputTexto(
                        estado.value.cp,
                        { nuevoTexto -> btVM.setCp(nuevoTexto) },
                        keyBoardType = KeyboardType.Number
                    )
                    Etiqueta("Estado*")
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopStart)
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .clickable(onClick = { btVM.setExpanded(true) })
                            .background(color = MaterialTheme.colorScheme.onTertiary)

                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                        ) {
                            UsuarioDisplay(
                                text = selectedOptionText,
                                modifier = Modifier.weight(4f)
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .size(30.dp)
                                    .weight(1f)
                            )
                        }
                        DropdownMenu(
                            expanded = estado_expanded.value,
                            onDismissRequest = { btVM.setExpanded(false) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            estadosMexico.forEach { selectionOption ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        btVM.setEstado(selectionOption)
                                        btVM.setExpanded(false)
                                    },
                                    text = { Text(selectionOption) }
                                )
                            }
                        }
                    }

                    //item{InputTexto(estado.value.estado,{ nuevoTexto -> btVM.setEstado(nuevoTexto)})}
                    ElevatedButton(
                        {
                            btVM.changeAddress()
                            navController.navigateUp()
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                    ) {
                        Text(text = "Guardar", color = MaterialTheme.colorScheme.onTertiary)
                    }
                }
            }
        }
        if(scrollPosition != maxScrollPosition) {
            Box(modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                //.padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)

            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Generar",
                    Modifier.size(30.dp),
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 5.dp))
}