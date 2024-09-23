package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

@Composable
fun Producto(btVM: BTVM, modifier: Modifier ){
    val estadoSeleccionado by btVM.estadoSeleccionado.collectAsState()
    val estadoListaProducto by btVM.estadoListaProducto.collectAsState()
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ){

            Titulo(titulo = estadoListaProducto[estadoSeleccionado].nombre)
            androidx.compose.foundation.Image(
                painter = BitmapPainter(Image(estadoListaProducto[estadoSeleccionado].imagen).asImageBitmap()),
                contentDescription = "Elemento",
                modifier = modifier.border(
                    2.dp,
                    MaterialTheme.colorScheme.tertiary,
                    RoundedCornerShape(25)
                )
            )
            Subtitulo(text = "$ ${estadoListaProducto[estadoSeleccionado].precio_normal}", fontSize = 25)
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Subtitulo(text = "Descripción")
            Text(text = estadoListaProducto[estadoSeleccionado].descripcion,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,)
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
            ElevatedCard(modifier = Modifier
                .width(100.dp)
                .height(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary)) {
                Row() {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Agregar",
                        modifier = Modifier
                            .padding(3.dp)
                            .clickable { /*TODO*/ }
                    )
                    Text(text = "0",
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(vertical = 3.dp), textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium,)
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Quitar",
                        modifier = Modifier
                            .padding(3.dp)
                            .clickable { /*TODO*/ }
                    )
                }
            }
            ElevatedButton(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )) {
                Text(text = "Agregar al carrito",)
            }
        }
    }
