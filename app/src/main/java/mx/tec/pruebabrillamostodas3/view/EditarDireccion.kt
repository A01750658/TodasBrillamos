package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

@Composable
fun EditarDireccion(btVM: BTVM){
    val estado = btVM.estadoUsuario.collectAsState()
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
                    .size(80.dp)
                    .fillMaxWidth(),

                )
            Titulo(
                titulo = "Editar Dirección",
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
                    .padding(bottom = 50.dp)

            ) {
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                )
                Subtitulo(
                    "Direccion",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onTertiary
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = MaterialTheme.colorScheme.onTertiary,

                    )

                LazyColumn {
                    item{Etiqueta("Calle*")}
                    item{Inputtexto(estado.value.direccion.calle,{/*TODO*/})}
                    item{
                        Row(modifier = Modifier.fillMaxWidth()){
                            Column(modifier = Modifier.weight(1f)) {
                                Etiqueta("Numero Exterior*")
                                Inputtexto(estado.value.direccion.numero_ext.toString(), {/*TODO*/ })
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Etiqueta("Numero Interior")
                                Inputtexto(estado.value.direccion.numero_int.toString(), {/*TODO*/ })
                            }
                        }
                    }

                    item{Etiqueta("Colonia*")}
                    item{Inputtexto(estado.value.direccion.colonia,{/*TODO*/})}
                    item{Etiqueta("Municipio*")}
                    item{Inputtexto(estado.value.direccion.municipio,{/*TODO*/})}
                    item{Etiqueta("Codigo Postal*")}
                    item{Inputtexto(estado.value.direccion.cp.toString(),{/*TODO*/})}
                    item{Etiqueta("Estado*")}
                    item{Inputtexto(estado.value.direccion.estado,{/*TODO*/})}
                    item{ ElevatedButton({/*TODO*/},
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                        ){
                        Text(text = "Guardar", color = MaterialTheme.colorScheme.onTertiary)
                    }}
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }
}