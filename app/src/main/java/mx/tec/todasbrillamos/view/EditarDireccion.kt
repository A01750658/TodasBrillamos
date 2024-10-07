package mx.tec.todasbrillamos.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.tec.todasbrillamos.viewmodel.BTVM
import mx.tec.todasbrillamos.viewmodel.ValidationsVM

/**
 * Pantalla para editar la dirección a la que se enviarían los productos
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @param btVM Viewmodel principal de la aplicación
 * @param navController Controlador de navegación de la aplicación
 * @param validationsVM Viewmodel de validaciones de la aplicación
 */
@Composable
fun EditarDireccion(btVM: BTVM, navController: NavHostController, validationsVM: ValidationsVM){
    val scrollState = rememberScrollState()
    val scrollPosition = scrollState.value
    val maxScrollPosition = scrollState.maxValue
    val estado = btVM.estadoCopiaDireccion.collectAsState()
    val usuario = btVM.estadoUsuario.collectAsState()
    val estadoErrors = btVM.estadoErrors.collectAsState()
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
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(80.dp)
                        .fillMaxWidth(),

                    )
                Titulo(
                    titulo = "Editar Dirección",
                    color = MaterialTheme.colorScheme.secondaryContainer,
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
                        { nuevoTexto -> btVM.setCalle(nuevoTexto)
                        if(nuevoTexto.isEmpty() || validationsVM.validateSpecialCharacters(nuevoTexto)){
                            btVM.setErrorCalle(true)
                        } else{
                            btVM.setErrorCalle(false)
                        }
                        })
                    if (estadoErrors.value.errorCalle) {
                        Etiqueta("La calle no puede estar vacia y no puede contener caracteres especiales", modifier = Modifier.padding(bottom=10.dp))
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Etiqueta("Numero Exterior*")
                            InputTexto(
                                estado.value.numero_exterior,
                                { nuevoTexto -> btVM.setNumeroExt(nuevoTexto)
                                if(nuevoTexto == "" || !validationsVM.validateJustNumbers(nuevoTexto)){
                                    btVM.setErrorNumeroExt(true)
                                }else{
                                    btVM.setErrorNumeroExt(false)
                                }
                                },
                                keyBoardType = KeyboardType.Number
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Etiqueta("Numero Interior")
                            InputTexto(
                                estado.value.numero_int,
                                { nuevoTexto -> btVM.setNumeroInt(nuevoTexto)
                                if (!validationsVM.validateJustNumbers(nuevoTexto)){
                                    btVM.setErrorNumeroInt(true)
                                    } else{
                                        btVM.setErrorNumeroInt(false)
                                    }},
                                keyBoardType = KeyboardType.Number)
                        }
                    }
                    if (estadoErrors.value.errorNumeroExt || estadoErrors.value.errorNumeroInt) {
                        Etiqueta("El numero interior y exterior deben ser números y el número exterior no puede estar vacio", modifier = Modifier.padding(bottom = 10.dp))
                    }
                    Etiqueta("Colonia*")
                    InputTexto(estado.value.colonia,
                        { nuevoTexto -> btVM.setColonia(nuevoTexto)
                            if(nuevoTexto == "" || validationsVM.validateSpecialCharacters(nuevoTexto)){
                                btVM.setErrorColonia(true)
                            }else{
                                btVM.setErrorColonia(false)
                            }})
                    if (estadoErrors.value.errorColonia) {
                        Etiqueta("La colonia no puede estar vacia o tener caracteres especiales", modifier= Modifier.padding(bottom = 10.dp))
                    }
                    Etiqueta("Municipio*")
                    InputTexto(estado.value.municipio,
                        { nuevoTexto -> btVM.setMunicipio(nuevoTexto)
                            if(nuevoTexto == "" || validationsVM.validateSpecialCharacters(nuevoTexto)){
                                btVM.setErrorMunicipio(true)
                            }else{
                                btVM.setErrorMunicipio(false)
                            }})
                    if (estadoErrors.value.errorMunicipio) {
                        Etiqueta("El municipio no puede estar vacio o tener caracteres especiales", modifier=Modifier.padding(bottom=10.dp))
                    }
                    Etiqueta("Código Postal*")
                    InputTexto(
                        estado.value.cp,
                        { nuevoTexto -> btVM.setCp(nuevoTexto)
                            if(nuevoTexto.length !=5 || !validationsVM.validateJustNumbers(nuevoTexto) ){
                                btVM.setErrorCp(true)
                            }else{
                                btVM.setErrorCp(false)
                            }},
                        keyBoardType = KeyboardType.Number
                    )
                    if (estadoErrors.value.errorCp) {
                        Etiqueta("El código postal no puede estar vacio o tener caracteres especiales y debe tener 5 números", modifier=Modifier.padding(bottom = 10.dp))
                    }
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
                                        btVM.setErrorEstado(false)
                                    },
                                    text = { Text(selectionOption) }
                                )
                            }
                        }
                    }
                    if (estadoErrors.value.errorEstado) {
                        Etiqueta("El estado no puede estar vacio", modifier = Modifier.padding(bottom = 10.dp))
                    }
                    //item{InputTexto(estado.value.estado,{ nuevoTexto -> btVM.setEstado(nuevoTexto)})}
                    ElevatedButton(
                        {
                            if(!estadoErrors.value.errorCalle && !estadoErrors.value.errorColonia && !estadoErrors.value.errorCp && !estadoErrors.value.errorEstado && !estadoErrors.value.errorMunicipio
                                && !estadoErrors.value.errorNumeroExt && estadosMexico.contains(estado.value.estado)) {
                                if (estado.value.numero_int==""){
                                    btVM.setNumeroInt("0")
                                }
                                if (usuario.value.direccion.calle == "") {
                                    btVM.addAddress(estado.value)
                                } else {
                                    btVM.updateAddress(estado.value)
                                }
                                btVM.changeAddress()
                                navController.navigateUp()
                            } else{
                                if (estado.value.calle == "") {
                                    btVM.setErrorCalle(true)
                                }
                                if(estado.value.municipio == ""){
                                    btVM.setErrorMunicipio(true)
                                }
                                if (estado.value.estado == ""){
                                    btVM.setErrorEstado(true)
                                }
                            }
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.secondaryContainer),
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