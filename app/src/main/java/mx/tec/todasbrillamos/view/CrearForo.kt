package mx.tec.todasbrillamos.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.tec.todasbrillamos.viewmodel.BTVM

/**
 * Pantalla para nueva publicación en nuestro caso, publicar una pregunta que tengas sobre el tema
 * @author Santiago Chevez
 * @param navController Controlador de navegación
 * @param onClick Función que se ejecuta al hacer clic en el botón de publicación
 */

@Composable
fun CrearForo(btVM: BTVM, navController: NavHostController) {
    var pregunta by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val estadoSolicitarForo by btVM.estadoSolicitarForo.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
                modifier = Modifier
                    .padding(10.dp)
                    .size(75.dp)
                    .fillMaxWidth(),

                )
            Titulo(
                "Haz tu pregunta",
                color = MaterialTheme.colorScheme.secondaryContainer,
                fontSize = 90
            )
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            InputTexto(pregunta, {pregunta = it}, modifier = Modifier.padding(bottom = 16.dp), placeHolder = "Escribe tu pregunta ...")
            TextButton(
                onClick = {
                    println("Publicando $pregunta")
                    if (pregunta.isNotEmpty()) {
                        btVM.solicitarForo(pregunta)
                    }
                    showDialog = true
                },
                Modifier
                    .padding(horizontal = 100.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(35.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = "Publicar",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = true },
                title = {

                    if (estadoSolicitarForo.contains("Successful")) {
                        Text(
                            text = "¡Solicitud de pregunta exitosa!",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }else{
                        Text(
                            text = "¡Solicitud de pregunta fallida!",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Text(
                        text = if (estadoSolicitarForo.contains("Successful")) {
                            "¡Tu pregunta será revisada y podrá ser respondida dentro de poco!"
                        } else {
                            "Algo salió mal..."
                        }
                    )
                },
                confirmButton = {
                    // Centrar el botón usando un Box
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = androidx.compose.ui.Alignment.Center  // Centra el contenido
                    ) {
                        Button(onClick = {
                            if (estadoSolicitarForo.contains("Successful")){
                                navController.navigateUp()
                            }
                            showDialog = false
                        }) {
                            Text("Aceptar", color = MaterialTheme.colorScheme.onTertiary)
                        }
                    }
                }
            )
        }
    }
}