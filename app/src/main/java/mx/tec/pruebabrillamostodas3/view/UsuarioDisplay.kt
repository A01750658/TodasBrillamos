package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Función que muestra el nombre de usuario en la pantalla.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param Text Nombre que se mostrará en la pantalla.
 *
 */

@Composable
fun UsuarioDisplay(text: String, modifier: Modifier = Modifier, fontSize: Int = 22) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp)) // Borde redondeado
            .background(MaterialTheme.colorScheme.onTertiary)
            .height(50.dp),
        verticalArrangement = Arrangement.Center
    ) {

        // Texto de nombre de usuario que se muestra dentro de la columna
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = fontSize.sp
        )
    }
}