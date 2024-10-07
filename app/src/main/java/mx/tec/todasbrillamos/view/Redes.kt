package mx.tec.todasbrillamos.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import mx.tec.todasbrillamos.viewmodel.BTVM
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import mx.tec.todasbrillamos.R

/**
 * Esta es la pantalla de redes sociales de la organización.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Augusto
 *
 * @param vmodel Viewmodel principal de la aplicación.
 * @param context Contexto de la aplicación.
 * @param configuration Configuración de la pantalla.
 *
 */


@Composable
fun Redes(vmodel: BTVM) {
    val context = LocalContext.current // Contexto de la aplicación para manejar las acciones como abrir enlaces y llamadas
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            // Ícono de titulo de la pantalla
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
                    .fillMaxWidth(),
            )

            // Título de la pantalla
            Titulo(
                titulo = "Redes y Contacto",
                color = MaterialTheme.colorScheme.secondaryContainer,
                fontSize = 50
            )

            Spacer(modifier = Modifier.padding(6.dp).fillMaxWidth())

            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.padding(bottom = 16.dp)
            )

            // Bot+om y texto psts Facebook
            BotonTextandIcon(
                text = "Facebook",
                icon = ImageVector.vectorResource(id = R.drawable.facebook),
                onClick = {
                    vmodel.openWebPage("https://m.facebook.com/FundacionTodasBrillamos", context) { intent ->
                        context.startActivity(intent)
                    }
                },
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = if (screenOrientation == 1) 25 else 40
            )

            // Botón y texto para Instagram
            BotonTextandIcon(
                text = "Instagram",
                icon = ImageVector.vectorResource(id = R.drawable.instagram),
                onClick = {
                    vmodel.openWebPage("https://www.instagram.com/fundaciontodasbrillamos/?igshid=NTc4MTIwNjQ2YQ%3D%3D", context) { intent ->
                        context.startActivity(intent)
                    }
                },
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = if (screenOrientation == 1) 25 else 40
            )

            // Botón y texto para TikTok
            BotonTextandIcon(
                text = "TikTok",
                icon = ImageVector.vectorResource(id = R.drawable.tiktok),
                onClick = {
                    vmodel.openWebPage("https://www.tiktok.com/@todas.brillamos", context) { intent ->
                        context.startActivity(intent)
                    }
                },
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = if (screenOrientation == 1) 25 else 40
            )

            // Botón y texto para WhatsApp
            BotonTextandIcon(
                text = "Whatsapp",
                icon = ImageVector.vectorResource(id = R.drawable.wattsapp),
                onClick = {
                    vmodel.openWebPage("https://wa.me/525628083883", context) { intent ->
                        context.startActivity(intent)
                    }
                },
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = if (screenOrientation == 1) 25 else 40
            )

            // Botón y texto para YouTube
            BotonTextandIcon(
                text = "YouTube",
                icon = ImageVector.vectorResource(id = R.drawable.youtube),
                onClick = {
                    vmodel.openWebPage("https://www.youtube.com/@FundacionTodasBrillamos", context) { intent ->
                        context.startActivity(intent)
                    }
                },
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                fontSize = if (screenOrientation == 1) 25 else 40
            )

            // Botón y texto para telefóno
            BotonTextandIcon(
                text = "Telefono",
                icon = Icons.Default.Phone,
                onClick = { vmodel.llamada("+52 56 2808 3883", context) },
                color = MaterialTheme.colorScheme.primaryContainer.copy(0.8f)
            )

            // Botón y texto para correo electrónico
            BotonTextandIcon(
                text = "Correo electrónico",
                icon = Icons.Default.Email,
                onClick = { vmodel.enviarCorreo("Contacto@fundaciontodasbrillamos.org", context) },
                color = MaterialTheme.colorScheme.secondaryContainer.copy(0.8f)
            )

            // Botón y texto para la ubicación
            BotonTextandIcon(
                text = "Ubicación",
                icon = Icons.Default.LocationOn,
                onClick = { vmodel.ubicacion("Fundación Todas Brillamos", context) },
                color = MaterialTheme.colorScheme.tertiary.copy(0.8f)
            )
        }
    }
}