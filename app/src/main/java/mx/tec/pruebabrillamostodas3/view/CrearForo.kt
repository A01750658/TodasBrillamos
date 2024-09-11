package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CrearForo(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
    ) {
        Column {
            Titulo(
                "Crea tu pregunta",
                color = MaterialTheme.colorScheme.primaryContainer,
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
            Etiqueta(
                "Categoría",
                Modifier.padding(bottom = 3.dp),
                color = Color.Black,
                padding = 30
            )
            Inputtexto("", {}, modifier = Modifier.padding(bottom = 16.dp))
            Etiqueta(
                "Pregunta",
                Modifier.padding(bottom = 3.dp),
                color = Color.Black,
                padding = 30
            )
            Inputtexto("", {}, modifier = Modifier.padding(bottom = 16.dp))
            TextButton(
                onClick = { /*TODO*/ },
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
        FloatingActionButton(
            onClick = { onClick() },
            containerColor = MaterialTheme.colorScheme.tertiary,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Generar"
            )

        }
    }
}