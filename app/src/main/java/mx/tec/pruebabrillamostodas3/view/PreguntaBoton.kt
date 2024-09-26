package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Santiago Chevez
 * Este Boton sirve para mostrar un texto y luego un botón
 * @param textPregunta texto de la pregunta
 * @param textBoton texto del botón
 * @param onClick acción del botón
 */
@Composable
fun PreguntaBoton(textPregunta: String, textBoton: String, onClick: ()-> Unit, modifier: Modifier=Modifier, color:Color= MaterialTheme.colorScheme.onTertiary){
    Row {
        Text(
            text = textPregunta,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(start = 16.dp, top = 13.dp)
                .weight(3f),
            color = color,
            style = MaterialTheme.typography.bodySmall
        )
        TextButton(onClick = { onClick() }, modifier = Modifier.weight(2f)) {
            Text(
                text = textBoton,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 15.sp,
                color = color
            )
        }
    }
}