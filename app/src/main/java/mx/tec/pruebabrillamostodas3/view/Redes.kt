package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.ui.platform.LocalContext


/**
 * @author Santiago Chevez
 * @author Alan Vega
 */


@Composable
fun Redes(vmodel: BTVM){
    val context = LocalContext.current // Obtener el contexto
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState)
    ){
        Column(modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth())
        {
            Titulo("ZAZIL", color = MaterialTheme.colorScheme.primaryContainer, fontSize = 90)
            Subtitulo("Cambia el mundo con un solo gesto.")
            HorizontalDivider(thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 16.dp))
            BotonTextandIcon(
                text = "Facebook",
                icon = Icons.Default.Share,
                onClick = {
                    vmodel.openWebPage("https://m.facebook.com/FundacionTodasBrillamos", context) { intent ->
                        context.startActivity(intent)
                    }},
                color = MaterialTheme.colorScheme.onPrimary
            )
            BotonTextandIcon(
                text = "Instagram",
                icon = Icons.Default.Share,
                onClick = {vmodel.openWebPage("https://www.instagram.com/fundaciontodasbrillamos/?igshid=NTc4MTIwNjQ2YQ%3D%3D", context) { intent ->
                    context.startActivity(intent)
                }},
                color = MaterialTheme.colorScheme.onSecondary
            )
            BotonTextandIcon(
                text = "TikTok",
                icon = Icons.Default.Share,
                onClick = { vmodel.openWebPage("https://vm.tiktok.com/ZMjKEqyJH/", context) { intent ->
                    context.startActivity(intent)
                } },
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            BotonTextandIcon(
                text = "Whatsapp",
                icon = Icons.Default.Share,
                onClick = { vmodel.openWebPage("https://wa.me/525628083883", context) { intent ->
                    context.startActivity(intent)
                } },
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            BotonTextandIcon(
                text = "YouTube",
                icon = Icons.Default.Share,
                onClick = { vmodel.openWebPage("https://www.youtube.com/@FundacionTodasBrillamos", context) { intent ->
                    context.startActivity(intent)
                }},
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }

    }
}