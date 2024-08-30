package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Etiqueta(texto: String, modifier: Modifier = Modifier) {

        Text(
            text = texto,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

}