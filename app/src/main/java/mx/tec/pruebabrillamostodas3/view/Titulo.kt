package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Titulo(modifier: Modifier = Modifier){
    Column{
        Text(
            text = "ZAZIL",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = "Cambia al mundo con solo un gesto",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = modifier.fillMaxWidth()
        )
    }
}