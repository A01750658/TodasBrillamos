package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
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
            .background(MaterialTheme.colorScheme.secondary),

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
                items(estadoListaProducto.value) { producto ->
                    // Mostrar el producto
                    BotonProducto(
                                onClick = { /*TODO*/ },
                                imagen = producto.imagen,
                                nombre = producto.nombre,
                                precio_n = producto.precio_normal,
                                precio_r = producto.precio_rebajado,
                                rebaja = producto.rebaja,
                                modifier = Modifier.fillMaxWidth()
                            )
                }
            }
            if (estadoListaProducto.value.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                println(estadoListaProducto.value.size)}
//            } else if (estadoCantidad.value == estadoListaProducto.value.size ){
//                LazyColumn {
//                    estadoListaProducto.value.forEach { producto ->
//                        item {
//                            BotonProducto(
//                                onClick = { /*TODO*/ },
//                                imagen = producto.imagen,
//                                nombre = producto.nombre,
//                                precio_n = producto.precio_normal,
//                                precio_r = producto.precio_rebajado,
//                                rebaja = producto.rebaja,
//                                modifier = Modifier.fillMaxWidth()
//                            )
//                        }
//                    }
//                }
//            } else{
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//                println(estadoListaProducto.value.size)
//                println("Ya cargaron algunos")
//            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getProductos()
    }
}
