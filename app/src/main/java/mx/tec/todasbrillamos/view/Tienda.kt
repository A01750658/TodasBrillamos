package mx.tec.todasbrillamos.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.tec.todasbrillamos.viewmodel.BTVM
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog

/**
 * Función que se encarga de mostrar la pantalla principal de los catalogos de productos, que incluye
 * un menú para filtrar por categoría, y botones flotantes para la navegación al carrito y al historial de compras.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param estadoListaProducto Lista de productos a mostrar.
 * @param estadoCantidad Cantidad de productos en el carrito.
 * @param showMenu Indicador de si se muestra el menú de producto.
 * @param estadoCategorias Lista de categorías de productos.
 * @param categorySelected Indicador de si se ha seleccionado una categoría.
 * @param selectedCategoria Categoría seleccionada.
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tienda(viewModel: BTVM, modifier: Modifier, navController: NavHostController) {

    // Variables del estado de la lista de productos, número de productos en el carrito,
    // la visibilidad del menú de categorías y la categoría seleccionada.
    val estadoListaProducto = viewModel.estadoListaProducto.collectAsState()
    val estadoCantidad by viewModel.estadoCantidadProductosModelo.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val estadoCategorias = viewModel.estadoCategorias.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    val selectedCategoria by viewModel.categoriaSeleccionada.collectAsState()
    var categoriaSeleccionada= selectedCategoria
    if (selectedCategoria == "Todas") {
        categoriaSeleccionada = "Categoría"
    }
    val filtroPrecio by viewModel.estadoFiltroPrecio.collectAsState()
    var filtroPrecioSeleccionado = "Precio"
    when (filtroPrecio) {
        0 -> {
            filtroPrecioSeleccionado = "Precio"
        }
        1 -> {
            filtroPrecioSeleccionado = "Menor a mayor"
        }
        -1 -> {
            filtroPrecioSeleccionado = "Mayor a menor"
        }
    }
    viewModel.filtrar_por_precio()
    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation

    // Contenedor principal de la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth() // Ocupa todo el ancho disponible
        ) {
            if (screenOrientation == 1) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .fillMaxWidth(),
                )
                Titulo(titulo = "Catálogo", color = MaterialTheme.colorScheme.secondaryContainer, fontSize = 50)
                Spacer(modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth())
                HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
                Spacer(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth())
                
                Row {
                    // Menú desplegable para filtrar productos por categoría
                    Box(modifier = Modifier.padding(8.dp).weight(1f)) {
                        Button(onClick = { expanded = true }) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = categoriaSeleccionada,
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    modifier= Modifier.weight(2f)
                                )
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Arrow down icon",
                                    modifier = Modifier.padding(start = 8.dp).weight(1f),
                                    tint = MaterialTheme.colorScheme.onTertiary
                                )
                            }
                        }

                        // Menú que muestra las categorías
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Opción para mostrar todos los productos
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Categoría",
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                onClick = {
                                    viewModel.setCategoriaSeleccionada("Todas")
                                    viewModel.resetListaFiltradaPorCategoria()
                                    expanded = false
                                }
                            )
                            // Opciones por categoría
                            estadoCategorias.value.forEach { categoria ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            categoria,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    },
                                    onClick = {
                                        viewModel.setCategoriaSeleccionada(categoria)
                                        viewModel.setListaFiltradaPorCategoria(categoria)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Box(modifier = Modifier.padding(8.dp).weight(1f)) {
                        Button(onClick = { expanded2 = true }) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = filtroPrecioSeleccionado,
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    modifier= Modifier.weight(2f)
                                )
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Arrow down icon",
                                    modifier = Modifier.padding(start = 8.dp).weight(1f),
                                    tint = MaterialTheme.colorScheme.onTertiary

                                )
                            }
                        }


                        DropdownMenu(
                            expanded = expanded2,
                            onDismissRequest = { expanded2 = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Precio",
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                onClick = {
                                    viewModel.setFiltrarPrecio(0)
                                    expanded2 = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Menor a mayor",
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                },
                                onClick = {
                                    viewModel.setFiltrarPrecio(1)
                                    expanded2 = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Mayor a menor",
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                },
                                onClick = {
                                    viewModel.setFiltrarPrecio(-1)
                                    expanded2 = false
                                }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(8.dp).fillMaxWidth())

            // Lista de productos
            LazyColumn {
                items((estadoListaProducto.value.size + 1) / 2) { rowIndex ->
                    LazyRow(Modifier.fillMaxWidth()) {
                        for (colIndex in 0..1) {
                            val index = rowIndex * 2 + colIndex
                            if (index < estadoListaProducto.value.size) {
                                item {
                                    BotonProducto(
                                        onClick = {
                                            viewModel.setEstadoSeleccionado(index)
                                            showDialog = true
                                        },
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

            // Circulo de carga si la lista de productos está vacía
            if (estadoListaProducto.value.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }

        // Botón para navegar al carrito de compras
        FloatingActionButton(
            onClick = { navController.navigate(Pantallas.RUTA_CARRITO) },
            containerColor = MaterialTheme.colorScheme.tertiary,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp),
        ) {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Generar")
        }

        // Popup que muestra la información del producto seleccionado
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {  // Cierra el diálogo cuando se toca fuera de él
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(35.dp))
                        .background(Color.White)  // Fondo blanco para el diálogo
                ) {
                    Producto(viewModel, modifier, navController)  // Muestra el contenido del producto dentro del diálogo
                }
            }
        }

        // Botón para navegar al historial de compras
        FloatingActionButton(
            onClick = {
                viewModel.getOrderHistory()
                navController.navigate(Pantallas.RUTA_HISTORIAL) },
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp, start = 16.dp),
        ) {
            Icon(imageVector = Icons.Default.List, contentDescription = "Generar", tint = MaterialTheme.colorScheme.onTertiary)
        }
    }
}