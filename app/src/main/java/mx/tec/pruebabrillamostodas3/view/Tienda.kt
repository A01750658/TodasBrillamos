package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
 * @author Santiago Chevez
 * @author Alan Vega
 */


@Composable
fun Tienda(viewModel: BTVM, modifier: Modifier){
    val estadoListaProducto = viewModel.estadoListaProducto.collectAsState()
    val estadoCantidad by viewModel
        .estadoCantidad.collectAsState()
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
                items((estadoListaProducto.value.size + 2) / 3) { rowIndex ->
                    LazyRow(Modifier.fillMaxWidth()) {
                        for (colIndex in 0..2) {
                            val index = rowIndex * 3 + colIndex
                            if (index < estadoListaProducto.value.size) {
                                item {
                                    BotonProducto(
                                        onClick = { /*TODO*/ },
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
            onClick = { /*TODO*/ },
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
    }

}
