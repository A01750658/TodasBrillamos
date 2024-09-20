package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.text.style.TextAlign

/**
 * @author Santiago Chevez
 * @author Alan Vega
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tienda(viewModel: BTVM, modifier: Modifier, navController: NavHostController){
    val estadoListaProducto = viewModel.estadoListaProducto.collectAsState()
    val estadoCantidad by viewModel.estadoCantidad.collectAsState()
    var showMenu by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)

        ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()

        ) {
            Titulo(
                "ZAZIL",
                color = MaterialTheme.colorScheme.primaryContainer,
                fontSize = 90
            )
            Subtitulo("Cambia el mundo con un solo gesto.")
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            LazyColumn {
                items((estadoListaProducto.value.size + 1) / 2) { rowIndex ->
                    LazyRow(Modifier.fillMaxWidth()) {
                        for (colIndex in 0..1) {
                            val index = rowIndex * 2 + colIndex
                            if (index < estadoListaProducto.value.size) {
                                item {
                                    BotonProducto(
                                        onClick = { showMenu = true },
                                        imagen = estadoListaProducto.value[index].imagen,
                                        nombre = estadoListaProducto.value[index].nombre,
                                        precio_n = estadoListaProducto.value[index].precio_normal,
                                        precio_r = estadoListaProducto.value[index].precio_rebajado,
                                        rebaja = estadoListaProducto.value[index].rebaja,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }
                    }
                }

            }
            if (estadoListaProducto.value.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                println(estadoListaProducto.value.size)}


        }
        FloatingActionButton(
            onClick = { navController.navigate("Carrito") },
            containerColor = MaterialTheme.colorScheme.tertiary,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Generar"
            )

        }
        if (showMenu) {
            ModalBottomSheet(
                onDismissRequest = { showMenu = false }
            ) {
                Producto(viewModel, modifier)
            }
        }
    }

}
