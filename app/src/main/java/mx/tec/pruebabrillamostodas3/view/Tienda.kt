package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
 * @author Alan Vega
 */

@Composable
fun Tienda(viewModel: BTVM, modifier: Modifier){
    val estadoLista = viewModel.estadoLista.collectAsState()
    LazyColumn{
        estadoLista.value.forEach{
            producto ->
            item{
                BotonElemento(onClick = { /*TODO*/ }, imagen = producto.imagen, nombre = producto.nombre, precio_n = producto.precio_normal, precio_r = producto.precio_rebajado, rebaja = producto.rebaja, modifier)
            }
        }
    }
}