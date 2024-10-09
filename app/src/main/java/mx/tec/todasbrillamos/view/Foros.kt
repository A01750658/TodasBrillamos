package mx.tec.todasbrillamos.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.tec.todasbrillamos.viewmodel.BTVM
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.ui.draw.clip

/**
 * Pantalla donde se muestran las publicaciones en el foro en nuestro caso preguntas frecuentes o preguntas realizadas por los usuarios
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Alan Vega
 * @param navController controlador de navegación de la aplicación
 */
@Composable
fun Foros(btVM: BTVM, navController: NavHostController) {

    //Variable para concocener la orientación de la pantalla
    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation
    //Estado Foro
    val estadoListaForo by btVM.estadoForo.collectAsState()
    //Estado Busqueda
    val estadoBusqueda by btVM.estadoBusquedaForo.collectAsState()
    val colors: List<Color> = listOf( MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.secondaryContainer)
    val scrollState = rememberScrollState()
    val scrollPosition = scrollState.value
    val maxScrollPosition = scrollState.maxValue

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
    )
    {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()

        ) {
            //Si la orientación es vertical se muestra el titulo y el icono
            if (screenOrientation == 1) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .fillMaxWidth(),

                    )
                Titulo(
                    titulo = "Preguntas Frecuentes",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    fontSize = 50,
                    lineHeight = 40
                )
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }
            InputPregunta(text =estadoBusqueda, onValueChange ={ nuevoTexto -> btVM.setEstadoBusquedaForo(nuevoTexto)
                println(estadoBusqueda)} )
            HorizontalDivider(
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )
            //Se muestran las publicaciones
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.verticalScroll(scrollState)){
                    for (foro in estadoListaForo) {
                        if (estadoBusqueda.isEmpty() || foro.pregunta.contains(estadoBusqueda, ignoreCase = true)) {
                            ElevatedCard(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable(onClick = {
                                        btVM.getComments(foro.id)
                                        navController.navigate(Pantallas.RUTA_FORO + "/${foro.id}")
                                    }),
                                colors = cardColors(
                                    containerColor = colors[foro.id % colors.size].copy(alpha = 0.5f)
                                )
                            ) {
                                Text(
                                    text = foro.pregunta,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp)
                                )
                            }
                        }
                    }
                }
                //Se muestra el boton para crear una nueva publicación
                FloatingActionButton(
                    onClick = { navController.navigate("CrearForo") },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Generar"
                    )

                }
                if(scrollPosition != maxScrollPosition) {
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                        //.padding(bottom = 20.dp)
                        .align(Alignment.BottomCenter)

                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Generar",
                            Modifier.size(30.dp),
                        )
                    }
                }
            }
        }
    }
}


