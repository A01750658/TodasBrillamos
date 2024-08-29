package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
 * @author Alan Vega
 */

@Composable
fun Home(btVM: BTVM){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ){
      Text(
          text = "Pantalla de Home xD",
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth()
      )
    }
}