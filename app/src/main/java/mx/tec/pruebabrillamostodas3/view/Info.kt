package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * @author Alan Vega
 * @author Santiago Chevez
 */

@Composable
fun Info() {
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
            LazyRow {
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .width(375.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "Nuestra Misión: \n" +
                                    "En Zazil, no solo estamos redefiniendo la menstruación, sino también el" +
                                    " impacto que tiene en la economía y el medio ambiente. Nuestra misión e" +
                                    "s empoderar a las mujeres a tomar decisiones informadas sobre su salud " +
                                    "menstrual mientras generan un impacto positivo en su bienestar financie" +
                                    "ro y en el planeta.",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        )
                    }
                }
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .width(375.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Nuestra Visión: \n" +
                                    "Imaginamos un mundo donde la menstruación no solo es sostenible para" +
                                    " el planeta, sino también empoderadora para todas las mujeres. Quer" +
                                    "emos que cada elección consciente de Zazil contribuya a la creación " +
                                    "de comunidades fuertes, mujeres empoderadas económicamente y un ento" +
                                    "rno más saludable y equitativo. Nuestra visión es que Zazil no sea so" +
                                    "lo un producto, sino una fuerza positiva que transforma la forma en q" +
                                    "ue vivimos la menstruación, promoviendo el bienestar personal y global",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        )
                    }
                }
            }
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Sobre la marca",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Lavado de Toallas",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                }
//                            AndroidView(
//                                factory = { context ->
//                                    WebView(context).apply {
//                                        webViewClient = object : WebViewClient() {
//                                            override fun shouldOverrideUrlLoading(
//                                                view: WebView?,
//                                                request: WebResourceRequest?
//                                            ): Boolean {
//                                                val url = request?.url.toString()
//                                                return if (url.startsWith("http://") || url.startsWith("https://")) {
//                                                    view?.loadUrl(url)
//                                                    false
//                                                } else {
//                                                    true
//                                                }
//                                            }
//                                        }
//                                        settings.javaScriptEnabled = true
//                                        settings.cacheMode = WebSettings.LOAD_NO_CACHE
//                                        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//                                        //loadUrl("https://") // Replace with your TikTok embed URL
//                                    }
//                                },
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .padding(8.dp)
//                            )
            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Preguntas Frecuentes",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                )
            }
        }
    }
}