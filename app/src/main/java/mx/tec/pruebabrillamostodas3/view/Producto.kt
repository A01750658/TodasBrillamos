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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.navigation.NavController
import mx.tec.pruebabrillamostodas3.viewmodel.EstadoProducto

/**
 * Pop Up del producto seleccionado que muestra los detalles en la tienda. Incluye la imagen del producto,
 * descripción, precio, cantidad disponible y opciones para agregar o quitar unidades antes de añadirlo al carrito de compras.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param btVM Viewmodel principal de la aplicación.
 * @param producto Producto seleccionado.
 * @param estadoSeleccionado Estado del producto seleccionado.
 * @param estadoListaProducto Estado de la lista de productos.
 * @param estadoAnadirProducto Estado de la cantidad de productos a añadir.
 *
 */
@Composable
fun Producto(btVM: BTVM, modifier: Modifier, navController: NavController ){
    // Obtiene el estado actual del producto seleccionado, la lista de productos y la cantidad a añadir
    val estadoSeleccionado by btVM.estadoSeleccionado.collectAsState()
    val estadoListaProducto by btVM.estadoListaProducto.collectAsState()
    val estadoAnadirProducto by btVM.estadoAnadirCarrito.collectAsState()
    val producto: EstadoProducto = estadoListaProducto[estadoSeleccionado]

    btVM.setEstadoAnadirCarrito(producto)
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ){
        Titulo(titulo = estadoListaProducto[estadoSeleccionado].nombre, lineHeight = 48)
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item{Image(
                painter = BitmapPainter(Image(estadoListaProducto[estadoSeleccionado].imagen).asImageBitmap()),
                contentDescription = "Elemento",
                modifier = Modifier.border(
                    2.dp,
                    MaterialTheme.colorScheme.tertiary,
                    RoundedCornerShape(25)).padding(16.dp)
            )}
            item {
                Subtitulo(
                    text = "$${estadoListaProducto[estadoSeleccionado].precio_normal} MXN",
                    fontSize = 25
                )
            }
            item {
                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }
            item {
                Subtitulo(text = "Descripción")
            }
            item {
                Text(
                    text = estadoListaProducto[estadoSeleccionado].descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                )
            }
            item {
                Row(modifier = Modifier.padding(vertical = 10.dp)) {
                    Text(
                        text = "Cantidad disponible: ",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    Text(
                        text = "${estadoListaProducto[estadoSeleccionado].cantidad}  unidades",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }
            item {
                ElevatedCard(
                    modifier = Modifier
                        .width(100.dp)
                        .height(30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Row() {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Agregar",
                            modifier = Modifier
                                .padding(3.dp)
                                .clickable { btVM.sumarorestarproducto(1, producto)}
                        )
                        Text(
                            text = "${estadoAnadirProducto.second}",
                            Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Quitar",
                            modifier = Modifier
                                .padding(3.dp)
                                .clickable { btVM.sumarorestarproducto(-1, producto) }
                        )
                    }
                }
            }
            item {
                ElevatedButton(
                    onClick = {
                        println("Añadiendo producto ${estadoListaProducto[estadoSeleccionado].id}")
                        btVM.addProducto(producto, estadoAnadirProducto.second)
                        navController.navigate("Carrito")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Text(text = "Agregar al carrito",)
                }
            }
        }
    }
}