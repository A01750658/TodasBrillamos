package mx.tec.todasbrillamos.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.tec.todasbrillamos.viewmodel.BTVM

/**
 * Pantalla que nos muestra el historial de compras del usuario
 * @author Andrés Cabrera
 * @author Santiago Chevez
 * @param viewModel ViewModel de la aplicación
 * @param modifier Modificador para personalizar la apariencia de la pantalla
 * @param navController Controlador de navegación para navegar entre pantallas
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Historial(viewModel: BTVM, modifier: Modifier, navController: NavController) {
    val configuration = LocalConfiguration.current
    // Obtenemos la orientación de la pantalla
    val screenOrientation = configuration.orientation
    var showMenu by remember { mutableStateOf(false) }
    val estadoHistorialOrden by viewModel.estadoHistorialOrden.collectAsState()
    val ordenesList = estadoHistorialOrden.toList()
    val scrollState = rememberScrollState()
    val scrollPosition = scrollState.value
    val maxScrollPosition = scrollState.maxValue

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .fillMaxWidth()
        ) {
            // Si la orientación de la pantalla es horizontal se muestra el icono y el título
            if (screenOrientation == 1) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .fillMaxWidth(),
                )
                Titulo(
                    titulo = "Historial de Compras",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    fontSize = 50
                )
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                ElevatedCard(
                    modifier = Modifier
                        .padding(1.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Row {
                        Text(
                            text = "# Orden",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                                .weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Fecha de Compra",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                                .weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Total",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                                .weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Estado",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                                .weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                // Tabla de la lista de órdenes
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                        .verticalScroll(scrollState)
                ) {
                    for (orden in estadoHistorialOrden) {
                        ElevatedCard(
                            onClick = {
                                viewModel.setEstadoSeleccionado(orden.key)
                                showMenu = true
                            },
                            modifier = Modifier
                                .padding(1.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onTertiary
                            )
                        ) {
                            Row {
                                Text(
                                    text = orden.key.toString(),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp)
                                        .weight(2f),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = orden.value[0].fecha_pedido,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp)
                                        .weight(2f),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "$ ${orden.value[0].total}",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp)
                                        .weight(2f),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = orden.value[0].estado,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp)
                                        .weight(2f),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    HorizontalDivider(
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        if (showMenu) {
            ModalBottomSheet(onDismissRequest = { showMenu = false }) {
                Detalles(viewModel)
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
}