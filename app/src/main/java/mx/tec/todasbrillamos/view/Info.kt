package mx.tec.todasbrillamos.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.tec.todasbrillamos.R
import mx.tec.todasbrillamos.viewmodel.BTVM
import androidx.compose.foundation.Image

/**
 * Esta función es una pantalla que nos muestra la información más relevante de Zazil.
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Alan Vega
 * @param vModel viewModel principal de la aplicación
 */
@Composable
fun Info(vModel: BTVM) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.secondary)
        .height(20.dp)
        .verticalScroll(scrollState))
    {
        Column(horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)){
            Image(
                painter = painterResource(id = R.drawable.log),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(170.dp)
                    .padding(20.dp)
            )
            //Titulo("ZAZIL", color = MaterialTheme.colorScheme.secondaryContainer, fontSize = 90)
            Subtitulo("Cambia el mundo con un solo gesto.",fontSize = 20)
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )
            Subtitulo(text = "SOBRE ZAZIL", fontSize = 24)
            Carrusel(listOf("Sobre la marca","¿Cómo lo hacemos?","¿Qué estamos haciendo?", "Nuestra Misión", "¿Qué queremos que sea el mundo?", "Nuestra visión"),
                listOf("Zazil es una marca comprometida con el bienestar de las mujeres y el" +
                        "cuidado del medio ambiente. Su misión es proporcionar soluciones in" +
                        "novadoras y sostenibles para el período menstrual. ",
                    "A través de la creación de toallas femeninas reutilizables.",
                    "En Zazil, no solo estamos redefiniendo la menstruación, sino también el " +
                            "impacto que tiene en la economía y el medio ambiente.",
                    "Nuestra misión es empoderar a las mujeres a tomar decisiones informadas sobre su salud" +
                            "menstrual mientras generan un impacto positivo en su bienestar financie" +
                            "ro y en el planeta.",
                    "Imaginamos un mundo donde la menstruación no solo es sostenible para" +
                            " el planeta, sino también empoderadora para todas las mujeres. Quer" +
                            "emos que cada elección consciente de Zazil contribuya a la creación" +
                            "de comunidades fuertes, mujeres empoderadas económicamente y un ento" +
                            "rno más saludable y equitativo.",
                    "Nuestra visión es que Zazil no sea solo un producto, sino una fuerza positiva que tra" +
                            "nsforma la forma en que vivimos la menstruación, promoviendo el bienestar personal y global."),
                    listOf(MaterialTheme.colorScheme.tertiary,MaterialTheme.colorScheme.primaryContainer ,MaterialTheme.colorScheme.secondaryContainer),
                images=listOf(R.drawable.presentacion, R.drawable.presentacion1, R.drawable.imagfondo1, R.drawable.imagfondo2, R.drawable.imagfondo3, R.drawable.imagfondo4))
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )
            Subtitulo(text = "EMPODERAMIENTO ECONÓMICO", fontSize = 24)
            Carrusel(titulos = listOf("Ahorro a Largo Plazo:","Oportunidades de Emprendimiento:"),
                lista = listOf("Al invertir en Zazil, estás invirtiendo en un producto que dura. " +
                        " Olvídate de compras mensuales; nuestras toallas son una invers" +
                        "ión que ahorra dinero con el tiempo.",
                    "Zazil apoya programas que proporcionan oportunidades de emprendimiento" +
                    " para mujeres locales, contribuyendo así al empoderamiento económico " +
                            "en comunidades de todo el mundo."),
                colors = listOf(MaterialTheme.colorScheme.tertiaryContainer,MaterialTheme.colorScheme.primary),
                images = listOf(R.drawable.imagfondo5, R.drawable.imagfondo6))

            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )
            Subtitulo(text = "Sobre nuestros productos", fontSize = 24)
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        vModel.openWebPage(
                            "https://www.tiktok.com/@todas.brillamos/video/7271770038439251206?is_from_webapp=1&sender_device=pc&web_id=7403071454306338310",
                            context
                        ) { intent ->
                            context.startActivity(intent)
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            ) {
                Text(
                    text = "Da CLICK para aprender sobre el lavado de nuestros productos",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}