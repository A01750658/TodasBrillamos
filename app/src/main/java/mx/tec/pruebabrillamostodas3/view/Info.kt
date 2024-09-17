package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

/**
 * @author Alan Vega
 * @author Santiago Chevez
 */

@Composable
fun Info(vModel: BTVM) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.secondary)
        .verticalScroll(scrollState))
    {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()

        ) {
            Titulo("ZAZIL", color = MaterialTheme.colorScheme.primaryContainer, fontSize = 90)
            Subtitulo("Cambia el mundo con un solo gesto.")
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
            )
            ) {
                Column {
                    Text(
                        text = "Sobre la marca",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top=24.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 18.sp

                    )
                    Text(
                        text = "Zazil es una marca comprometida con el bienestar de las mujeres y el" +
                                " cuidado del medio ambiente. Su misión es proporcionar soluciones in" +
                                "novadoras y sostenibles para el período menstrual. ¿Cómo lo hacen? A" +
                                " través de la creación de toallas femeninas reutilizables.",
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 12.dp, top = 8.dp, bottom = 12.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            LazyRow {
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .width(360.dp)
                            .height(458.dp)
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Box (contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()){
                            Column {
                                Text(
                                    text = "Nuestra Misión:",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "En Zazil, no solo estamos redefiniendo la menstruación, sino también el" +
                                            " impacto que tiene en la economía y el medio ambiente. Nuestra misión e" +
                                            "s empoderar a las mujeres a tomar decisiones informadas sobre su salud " +
                                            "menstrual mientras generan un impacto positivo en su bienestar financie" +
                                            "ro y en el planeta.",
                                    textAlign = TextAlign.Justify,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 24.dp,
                                            end = 12.dp,
                                            top = 8.dp,
                                            bottom = 12.dp
                                        ),
                                    style = MaterialTheme.typography.bodySmall,

                                )
                            }
                        }
                    }
                }
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .width(360.dp)
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Column {
                            Text(
                                text = "Nuestra Visión:",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "Imaginamos un mundo donde la menstruación no solo es sostenible para" +
                                        " el planeta, sino también empoderadora para todas las mujeres. Quer" +
                                        "emos que cada elección consciente de Zazil contribuya a la creación " +
                                        "de comunidades fuertes, mujeres empoderadas económicamente y un ento" +
                                        "rno más saludable y equitativo. Nuestra visión es que Zazil no sea so" +
                                        "lo un producto, sino una fuerza positiva que transforma la forma en q" +
                                        "ue vivimos la menstruación, promoviendo el bienestar personal y global.",
                                textAlign = TextAlign.Justify,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp, end = 12.dp, top = 8.dp, bottom = 12.dp),
                                style = MaterialTheme.typography.bodySmall

                            )
                        }
                    }
                }
            }
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )
            Subtitulo(text = "EMPODERAMIENTO ECONÓMICO", fontSize = 24)
            LazyRow {
                item{
                    ElevatedCard(
                    modifier = Modifier
                        .width(360.dp)
                        .height(275.dp)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = Color.Black
                    )
                ) {
                    Column {
                        Text(
                            text = "Ahorro a Largo Plazo:",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Al invertir en Zazil, estás invirtiendo en un producto que dura." +
                                    " Olvídate de compras mensuales; nuestras toallas son una invers" +
                                    "ión que ahorra dinero con el tiempo.",
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 24.dp, end = 12.dp, top = 8.dp, bottom = 12.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                }
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .width(360.dp)
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Column {
                            Text(
                                text = "Oportunidades de Emprendimiento:",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                style = MaterialTheme.typography.bodyMedium,fontSize = 18.sp
                            )
                            Text(
                                text = "Zazil apoya programas que proporcionan oportunidades de emprendimiento" +
                                        " para mujeres locales, contribuyendo así al empoderamiento económico " +
                                        "en comunidades de todo el mundo.",
                                textAlign = TextAlign.Justify,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 24.dp,
                                        end = 12.dp,
                                        top = 8.dp,
                                        bottom = 12.dp
                                    ),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
            )
            Subtitulo(text = "Sobre nuestros productos", fontSize = 24)
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { vModel.openWebPage("https://www.tiktok.com/@todas.brillamos/video/7271770038439251206?is_from_webapp=1&sender_device=pc&web_id=7403071454306338310", context) { intent ->
                        context.startActivity(intent)
                    }},
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
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