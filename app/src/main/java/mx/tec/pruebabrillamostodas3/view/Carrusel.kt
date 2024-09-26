package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Carrusel(titulos: List<String>, lista: List<String>, colors: List<Color>, modifier: Modifier = Modifier){
    var currentImageIndex by remember { mutableStateOf(0) }
    Box(modifier = modifier) {
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colors[currentImageIndex%colors.size],
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            ) {
                Column {
                    Text(
                        text = titulos[currentImageIndex],
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 18.sp

                    )
                    Text(
                        text = lista[currentImageIndex],
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 12.dp, top = 8.dp, bottom = 70.dp),
                        style = MaterialTheme.typography.bodySmall
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
                    currentImageIndex = (currentImageIndex - 1 + lista.size) % lista.size
                }, modifier = Modifier.padding(bottom = 8.dp, start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors[(currentImageIndex + 1)% colors.size],
                        contentColor = MaterialTheme.colorScheme.onTertiary)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Anterior", tint = MaterialTheme.colorScheme.onTertiary)
                }

                Button(onClick = {
                    currentImageIndex = (currentImageIndex + 1) % lista.size
                }, modifier = Modifier.padding(bottom = 8.dp, end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = colors[(currentImageIndex +1)%colors.size],
                    contentColor = MaterialTheme.colorScheme.onTertiary)) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente", tint = MaterialTheme.colorScheme.onTertiary)
                }
            }
        }

}