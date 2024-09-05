package mx.tec.pruebabrillamostodas3.view

import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Foros(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.secondary))
    {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()

        ) {
            Titulo("ZAZIL", color = MaterialTheme.colorScheme.primaryContainer, fontSize = 90)
            Subtitulo("Cambia el mundo con un solo gesto.")
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primaryContainer)
            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Etiqueta("Buscar", Modifier.padding(bottom = 3.dp), color= Color.Black, padding = 30)
            //Checar si se puede hacer con searchbar
            Row {
                Inputtexto("", {}, modifier = Modifier.weight(5f))
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Busqueda",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { /*TODO*/ })
                        .size(50.dp)
                        .weight(1f)
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.primary, thickness = 2.dp)
            LazyColumn {
                item{
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Foro 1",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        )
                    }
                }
                item{
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Foro 2",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        )
                    }
                }
                item{
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Foro 3",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        )
                    }
                }
            }
        }

    }
}