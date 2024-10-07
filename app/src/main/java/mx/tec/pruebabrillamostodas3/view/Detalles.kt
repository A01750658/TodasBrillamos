package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.navigation.NavController
import mx.tec.pruebabrillamostodas3.viewmodel.EstadoProducto

/**
 * Función que nos muestra los detalles de alguna orden que se haya realizado
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @param btVM Viewmodel principal de la aplicación.
 * @param modifier Modificador
 * @param navController Controlador de navegación de la aplicación.
 */
@Composable
fun Detalles(btVM: BTVM, modifier: Modifier, navController: NavController){

    val estadoHistorialOrden by btVM.estadoHistorialOrden.collectAsState()
    val estadoSeleccionado by btVM.estadoSeleccionado.collectAsState()
    val productos = estadoHistorialOrden[estadoSeleccionado]
    println("ORDEN")
    println(estadoSeleccionado)
    var total=0
    if (productos != null) {
        for (producto in productos) {
            total += producto.total
        }
        //Tabla de los productos y el total que se pago
        LazyColumn(Modifier.fillMaxWidth()) {
            item {
                ElevatedCard(
                    modifier = Modifier
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Row {
                        Text(
                            text = "Producto",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                                .weight(3f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Cantidad",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                                .weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Precio",
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
            items(productos) { producto ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Row {
                        Column(
                            modifier = Modifier.weight(3f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = producto.nombre,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = producto.cantidad.toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "$${producto.total}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            item {
                ElevatedCard(
                    modifier = Modifier
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Row {
                        Text(
                            text = "Total:",
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                                .weight(3f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "$$total",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                                .weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}