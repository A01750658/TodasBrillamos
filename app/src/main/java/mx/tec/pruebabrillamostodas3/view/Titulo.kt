package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Titulo(titulo: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.primary){

        Text(
            text = titulo,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = color,
            fontSize = 48.sp,
            modifier = modifier.fillMaxWidth()
        )

}