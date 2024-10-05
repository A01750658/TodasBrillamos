package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.ui.graphics.Color

/**
 * Este es el template para todos los foros que vayan a haber en la aplicación
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Augusto
 *
 * @param Pregunta es la pregunta que se va a hacer en el foro
 * @param Respuestas es la lista de respuestas que se van a mostrar en el foro
 *
 */
@Composable
fun TempleteForo(btVM: BTVM, idForo: String) {
    // Estado listando las respuestas.
    val estadoForo by btVM.estadoForo.collectAsState()
    val estadoComentarios by btVM.estadoComentarios.collectAsState()
    var pregunta=""
    for (foro in estadoForo) {
        if (foro.id == idForo.toInt()) {
            pregunta = foro.pregunta
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Column {

            // Subtítulo que contiene la pregunta realizada en base al id del foro.
            Subtitulo(pregunta, fontSize = 40, lineHeight = 60)

            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer
            )

            Spacer(modifier = Modifier.padding(8.dp).fillMaxWidth() )

            // Indica la sección de respuestas.
            Subtitulo("Respuesta(s)", fontSize = 20, lineHeight = 40)

            // Lista de respuestas.
            LazyColumn {
                for (comentario in estadoComentarios) {
                    item {
                        ElevatedCard(
                            modifier = Modifier
                                .padding(8.dp),
                            colors = cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                            )
                        ) {
                            // Muestra cada respuesta centrada dentro de la tarjeta.
                            Text(
                                text = comentario.respuesta,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}