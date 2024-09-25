package mx.tec.pruebabrillamostodas3.view

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
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import mx.tec.pruebabrillamostodas3.R



/**
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 */


@Composable
fun Redes(vmodel: BTVM){
    val context = LocalContext.current // Obtener el contexto
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState)
    ){
        Column( horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth())
        {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
                    .fillMaxWidth(),

                )
            Titulo(titulo = "Redes", color = MaterialTheme.colorScheme.primaryContainer, fontSize = 50)
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )
            HorizontalDivider(thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 16.dp))
            BotonTextandIcon(
                text = "Facebook",
                icon = ImageVector.vectorResource(id = R.drawable.facebook),
                onClick = {vmodel.openWebPage("https://m.facebook.com/FundacionTodasBrillamos", context) { intent ->
                        context.startActivity(intent)
                    }
                },
                color = MaterialTheme.colorScheme.onPrimary, fontSize = if (screenOrientation == 1) 25 else 40
            )
            BotonTextandIcon(
                text = "Instagram",
                icon = ImageVector.vectorResource(id = R.drawable.instagram),
                onClick = {vmodel.openWebPage("https://www.instagram.com/fundaciontodasbrillamos/?igshid=NTc4MTIwNjQ2YQ%3D%3D", context) { intent ->
                    context.startActivity(intent)
                }},
                color = MaterialTheme.colorScheme.onSecondary, fontSize = if (screenOrientation == 1) 25 else 40
            )
            BotonTextandIcon(
                text = "TikTok",
                icon = ImageVector.vectorResource(id = R.drawable.tiktok),
                onClick = { vmodel.openWebPage("https://www.tiktok.com/@todas.brillamos", context) { intent ->
                    context.startActivity(intent)
                } },
                color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = if (screenOrientation == 1) 25 else 40
            )
            BotonTextandIcon(
                text = "Whatsapp",
                icon = ImageVector.vectorResource(id = R.drawable.wattsapp),
                onClick = { vmodel.openWebPage("https://wa.me/525628083883", context) { intent ->
                    context.startActivity(intent)
                } },
                color = MaterialTheme.colorScheme.onSecondaryContainer, fontSize = if (screenOrientation == 1) 25 else 40
            )
            BotonTextandIcon(
                text = "YouTube",
                icon = ImageVector.vectorResource(id = R.drawable.youtube),
                onClick = { vmodel.openWebPage("https://www.youtube.com/@FundacionTodasBrillamos", context) { intent ->
                    context.startActivity(intent)
                }},
                color = MaterialTheme.colorScheme.onTertiaryContainer, fontSize = if (screenOrientation == 1) 25 else 40
            )
        }

    }
}
