package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

@Composable
fun MenuDirecciones(btVM: BTVM, navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .padding(10.dp)
                    .size(75.dp)
                    .fillMaxWidth(),

                )
            Titulo(
                titulo = "Direcciones",
                color = MaterialTheme.colorScheme.primaryContainer,
                fontSize = 50
            )
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                )
                Subtitulo(
                    "Direcciones Registradas",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = MaterialTheme.colorScheme.onTertiary,

                )
                LazyColumn {

                        item {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .padding(horizontal = 16.dp)
                                    .background(MaterialTheme.colorScheme.onTertiary)
                                    .fillMaxWidth()
                                    .border(2.dp, MaterialTheme.colorScheme.tertiary)

                            ) {
                                Row {
                                    Text(
                                        "Vazco de Quiroga 2134, Central, Santa Fe, CP 5867",
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                            .padding(vertical = 20.dp)
                                            .weight(5f),
                                        style = MaterialTheme.typography.bodySmall,
                                        textAlign = TextAlign.Center,
                                    )
                                    Column(
                                        modifier = Modifier
                                            .weight(3f)
                                            .padding(vertical = 14.dp)
                                            .padding(start = 5.dp, end = 10.dp)
                                    ) {
                                        ElevatedButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier
                                                .padding(vertical = 1.dp)
                                                .height(50.dp)
                                                .width(110.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = MaterialTheme.colorScheme.tertiary,
                                                contentColor = MaterialTheme.colorScheme.onTertiary
                                            )
                                        ) {
                                            Text(
                                                "Editar",
                                                modifier = Modifier.height(20.dp),
                                                color = MaterialTheme.colorScheme.onTertiary,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                        ElevatedButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier
                                                .padding(vertical = 1.dp)
                                                .height(50.dp)
                                                .width(110.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = MaterialTheme.colorScheme.tertiary,
                                                contentColor = MaterialTheme.colorScheme.onTertiary
                                            )
                                        ) {
                                            Text(
                                                "Eliminar",
                                                modifier = Modifier.height(20.dp),
                                                color = MaterialTheme.colorScheme.onTertiary,
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }
                    }
                }
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
            }
        }
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp)),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Generar"
            )

        }
    }

}