package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
 * @author Santiago Chevez
 * @author Alan Vega
 * Pestaña con los datos de contacto
 */

@Composable
fun Contacto(vModel: BTVM) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState)
        ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()

        ) {
            Titulo(
                "ZAZIL",
                color = MaterialTheme.colorScheme.primaryContainer,
                fontSize = 90
            )
            Subtitulo("Cambia el mundo con un solo gesto.")
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.tertiary)

            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
                {
                    Subtitulo(
                        "Contacto",
                        Modifier.padding(bottom = 3.dp),
                        fontSize = 30,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                    BotonTextandIcon(
                        text = "Telefono",
                        icon = Icons.Default.Phone,
                        onClick = { vModel.llamada("+52 56 2808 3883", context)
                            },
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                    BotonTextandIcon(
                        text = "Correo electrónico",
                        icon = Icons.Default.Email,
                        onClick = { vModel.enviarCorreo("Contacto@fundaciontodasbrillamos.org", context) },
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                    BotonTextandIcon(
                        text = "Ubicación",
                        icon = Icons.Default.LocationOn,
                        onClick = { vModel.ubicacion("Fundación Todas Brillamos", context) },
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}