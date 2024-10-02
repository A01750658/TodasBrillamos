package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

/**
 * @author Santiago Chevez
 * Pantalla para nueva publicación en el foro
 */

@Composable
fun CrearForo(navController: NavHostController, onClick: () -> Unit) {
    var pregunta by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondaryContainer,
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
                    navController.navigateUp()
                          },
                Modifier.padding(horizontal = 100.dp)
                    .padding(bottom = 16.dp)
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

    }
}