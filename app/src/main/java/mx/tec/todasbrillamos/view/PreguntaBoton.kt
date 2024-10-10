package mx.tec.todasbrillamos.view

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
 * Este Boton sirve para mostrar un texto y luego un botón, con la finalidad de interactuar con el usuario a
 * realizar una acción basada en la pregunta que se le hace.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param textPregunta texto de la pregunta
 * @param textBoton texto del botón
 * @param onClick acción del botón
 *
 */
@Composable
fun PreguntaBoton(textPregunta: String, textBoton: String, onClick: ()-> Unit, modifier: Modifier=Modifier, color:Color= MaterialTheme.colorScheme.onTertiary){
    Row {
        Text( // Muestra el texto de la pregunta
            text = textPregunta, // Texto de la pregunta
            fontSize = 15.sp, // Tamaño de la fuente
            modifier = Modifier
                .padding(start = 16.dp, top = 13.dp)
                .weight(3f), // Peso del texto para equilibrar el contenido
            color = color,
            style = MaterialTheme.typography.bodySmall // Estilo del texto
        )
        // Botón que realiza la acción
        TextButton(onClick = { onClick() }, modifier = Modifier.weight(2f)) {
            Text(
                text = textBoton, // Texto del botón
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium, // Estilo del texto del botón
                fontSize = 18.sp, // Tamaño de la fuente
                color = color
            )
        }
    }
}