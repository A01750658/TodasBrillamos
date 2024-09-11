package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Santiago Chevez
 * @author Alan Vega
 */

@Composable
fun Perfil() {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState)
    ) {
        Column( horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
                    .fillMaxWidth(),

            )
            Titulo(titulo = "Perfil", color = MaterialTheme.colorScheme.primaryContainer, fontSize = 50)
            HorizontalDivider(thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 16.dp))
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )

            Column(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary)
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                )
                Etiqueta(
                    "Nombre",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary,
                    padding = 30
                )
                UsuarioDisplay(text = "Cesar Augusto")
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                )
                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Etiqueta(
                            "Apellido Paterno",
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onTertiary,
                            padding = 16
                        )
                        UsuarioDisplay(text = "Flores")
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Etiqueta(
                            "Apellido Materno",
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onTertiary,
                            padding = 16
                        )
                        UsuarioDisplay(text = "Reyes")
                    }
                }
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                )
                Etiqueta(
                    "Correo",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary,
                    padding = 30
                )
                UsuarioDisplay(text = "cesar@gmail.com")
                Spacer(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )
                TextButton(onClick = { /*TODO*/ },
                    Modifier
                        .padding(horizontal = 50.dp)
                        .padding(bottom = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.tertiary)
                ) {
                    Text(
                        text = "Actualizar o agregar direccion",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        }
    }
}