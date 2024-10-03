package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.tec.pruebabrillamostodas3.R

/**
 * Función que se encarga de hacer un tipo carrucel en el que se tiene una imagen de fondo, titulo, e información
 * Los parametros titulo y lista deben ser del mismo tamaño
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @param titulos Lista de los titulos de las diferentes pantallas
 * @param lista Lista de los textos que se muestran
 * @param colors Lista de los colores que se usan
 * @param images Lista de las imagenes que se usan para los botones de cambiar de pantalla
 * @param modifier Modificador de la función
 */
@Composable
fun Carrusel(titulos: List<String>, lista: List<String>, colors: List<Color>, images: List<Int>, modifier: Modifier = Modifier){
    var currentSlideIndex by remember { mutableStateOf(0) }
    var textcolor = MaterialTheme.colorScheme.onTertiary
    /*if (colors[currentSlideIndex%colors.size] == MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.8f)){
        textcolor = Color.Black
        println("Amarillo")
    }*/
    Box(modifier = modifier) {
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .height(300.dp)
                    .paint(
                        painterResource(id =images[currentSlideIndex%images.size]),
                        contentScale = ContentScale.FillBounds)
                    ,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    contentColor = textcolor
                )
            ) {
                Column {
                    Text(
                        text = titulos[currentSlideIndex],
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,

                    )
                    Text(
                        text = lista[currentSlideIndex],
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 24.dp, end = 12.dp, top = 8.dp, bottom = 70.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp
                    )
                }
            }

            // Add navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    currentSlideIndex = (currentSlideIndex - 1 + lista.size) % lista.size
                }, modifier = Modifier.padding(bottom = 8.dp, start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors[(currentSlideIndex + 1)% colors.size],
                        contentColor = MaterialTheme.colorScheme.onTertiary)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Anterior", tint = MaterialTheme.colorScheme.onTertiary)
                }

                Button(onClick = {
                    currentSlideIndex = (currentSlideIndex + 1) % lista.size
                }, modifier = Modifier.padding(bottom = 8.dp, end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = colors[(currentSlideIndex +1)%colors.size],
                    contentColor = MaterialTheme.colorScheme.onTertiary)) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente", tint = MaterialTheme.colorScheme.onTertiary)
                }
            }
        }

}