package mx.tec.todasbrillamos.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Etiqueta para nombrar los distintos inputs que hay en la aplicación
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @param texto texto a mostrar en la etiqueta
 * @param modifier modificador para personalizar la etiqueta
 * @param color color de la etiqueta
 * @param padding padding horizontal de la etiqueta
 */
@Composable
fun Etiqueta(texto: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onTertiary, padding: Int = 16) {

        Text(
            text = texto,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = color,
            modifier = modifier
                .padding(horizontal = padding.dp)
                .fillMaxWidth()
        )

}