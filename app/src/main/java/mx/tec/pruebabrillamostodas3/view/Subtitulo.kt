package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Subtitulo(text: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.primaryContainer,fontSize: Int = 16){
    Text(
        text = text,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = color,
        modifier = modifier.padding(15.dp)
            .fillMaxWidth()
    )
}