package mx.tec.todasbrillamos.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

/**
 * Función que se encarga de mostrar los titulos en las pantallas.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param titulo Titulo que se mostrará en la pantalla.
 *
 */

@Composable
fun Titulo(titulo: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.primary, fontSize: Int = 48, lineHeight: Int = 80){

    Text(
        text = titulo,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        color = color,
        fontSize = fontSize.sp,
        modifier = modifier.fillMaxWidth(),
        lineHeight = lineHeight.sp
    )

}