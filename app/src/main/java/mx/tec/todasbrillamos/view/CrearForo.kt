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
import mx.tec.todasbrillamos.viewmodel.ValidationsVM

/**
 * Pantalla para nueva publicación en nuestro caso, publicar una pregunta que tengas sobre el tema
 * @author Santiago Chevez
 * @param navController Controlador de navegación
 * @param onClick Función que se ejecuta al hacer clic en el botón de publicación
 */

@Composable
fun CrearForo(btVM: BTVM, navController: NavHostController, validationsVM: ValidationsVM) {
    var pregunta by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(true) } // Mostrar mensaje de advertencia al inicio
    var showSubmitDialog by remember { mutableStateOf(false) } // Mostrar diálogo tras publicar
    var submitState by remember { mutableStateOf("") }
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

            InputTexto(pregunta, { pregunta = it }, modifier = Modifier.padding(bottom = 16.dp), placeHolder = "Escribe tu pregunta ...")
            TextButton(
                onClick = {
                    println("Publicando $pregunta")
                    if (pregunta.isNotEmpty()) {
                        if (!validationsVM.validateForbiddenWords(pregunta) and !validationsVM.validateUrl(pregunta)) {
                            btVM.solicitarForo(pregunta)
                            submitState = "Successful"
                            showSubmitDialog = true
                        } else{
                            submitState = "Wrong"
                            showSubmitDialog = true
                        }
                    } else {
                        submitState = "Failed"
                        showSubmitDialog = true
                    }
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
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }

        // Mostrar advertencia al inicio
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = true },
                title = {
                    Text(
                        text = "Atención",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                text = {
                    Text(
                        text = "Es importante mantener las preguntas serias y un lenguaje apropiado en todo momento."
                    )
                },
                confirmButton = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Button(onClick = { showDialog = false }) {
                            Text("Aceptar", color = MaterialTheme.colorScheme.onTertiary)
                        }
                    }
                }
            )
        }

        // Mostrar el diálogo de resultado tras intentar publicar la pregunta
        if (showSubmitDialog) {
            AlertDialog(
                onDismissRequest = { showSubmitDialog = true },
                title = {
                    Text(
                        text = if (submitState == "Successful" && estadoSolicitarForo.contains("Successful")) {
                            "¡Solicitud de pregunta exitosa!"
                        } else if (submitState == "Wrong") {
                            "Su solicitud contiene palabras prohibidas o una URL"
                        }
                        else{
                                "¡Solicitud de pregunta fallida!"
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                text = {
                    Text(
                        text = if (submitState == "Successful" && estadoSolicitarForo.contains("Successful")) {
                            "¡Tu pregunta será revisada y podrá ser respondida dentro de poco!"
                        } else if (submitState == "Wrong") {
                            "¡No escribas grocerias!\n" +
                                    "No esta permitido mandar URL ni palabras prohibidas"
                        }
                        else {
                            "Algo salió mal..."
                        }
                    )
                },
                confirmButton = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Button(onClick = {
                            if (submitState == "Successful" && estadoSolicitarForo.contains("Successful")) {
                                navController.navigateUp()
                            }
                            showSubmitDialog = false
                        }) {
                            Text("Aceptar", color = MaterialTheme.colorScheme.onTertiary)
                        }
                    }
                }
            )
        }
    }
}
