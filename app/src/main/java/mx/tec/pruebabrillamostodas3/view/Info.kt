package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Label
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
* @author Alan Vega
 */

@Composable
fun Info(){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()){
       ElevatedCard(modifier = Modifier.fillMaxWidth().weight(1f).padding(16.dp)){
           Text(
               text = "Misión",
               textAlign = TextAlign.Center,
               modifier = Modifier.fillMaxSize().padding(24.dp)
           )
       }
        Row(modifier = Modifier.fillMaxWidth().weight(1f).fillMaxHeight(1f)){
            ElevatedCard(modifier = Modifier.fillMaxSize().weight(1f).padding(16.dp)){
                Text(
                    text = "Visión",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize().padding(24.dp)
                )
            }
            ElevatedCard(modifier = Modifier.fillMaxSize().weight(1f).padding(16.dp)){
                Text(
                    text = "Sobre la marca",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize().padding(24.dp)
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth().weight(1f).fillMaxHeight(1f)){
            ElevatedCard(modifier = Modifier.fillMaxSize().weight(1f).padding(16.dp)){
                Text(
                    text = "Lavado de Toallas",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize().padding(24.dp)
                )
            }
            ElevatedCard(modifier = Modifier.fillMaxSize().weight(1f).padding(16.dp)){
                Text(
                    text = "Futuro video de Tiktok, supongo",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize().padding(24.dp)
                )
            }
        }
        ElevatedCard(modifier = Modifier.fillMaxWidth().weight(1f).padding(16.dp)){
            Text(
                text = "Preguntas Frecuentes",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize().padding(24.dp)
            )
        }


    }
}