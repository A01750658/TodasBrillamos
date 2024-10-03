package mx.tec.pruebabrillamostodas3.view

import android.content.Context
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import mx.tec.pruebabrillamostodas3.viewmodel.PaymentsViewModel
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.paypal.base.rest.APIContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController

/**
 * Pantalla con el carrito de productos, en esta pantalla se muestran los productos que el usuario quiere comprar
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Alan Vega
 * @param viewModel ViewModel de la aplicación
 * @param navController Controlador de navegación
 */
@Composable
fun Carrito(viewModel: BTVM, navController: NavHostController){

    val estadoCarrito by viewModel.estadoCarrito.collectAsState()
    //Para saber la orientación de la pantalla
    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation
    //Suma el total a pagar
    var total=0f
    for (producto in estadoCarrito.productos){
        if (producto.first.rebaja==0){
            total += producto.first.precio_normal*producto.second
        } else{
            total += producto.first.precio_rebajado*producto.second
        }
    }

    viewModel.setTotalCarrito(total)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()

        ) {
            //Si esta vertical sí se muestra, de lo contrario, no se muestra
            if (screenOrientation == 1) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
            //Tabla del carrito
            LazyColumn(Modifier.fillMaxWidth()) {
                item {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            text = "Total a pagar: ",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(6f)
                        )
                        Text(
                            text = " $" +total.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f)
                        )
                    }
                }
                //Headers tabla
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Row {
                            Text(
                                text = "Producto",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp)
                                    .weight(3f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Cantidad",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp)
                                    .weight(2f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Precio",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp)
                                    .weight(2f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(text = "", modifier = Modifier.weight(1f))
                        }
                    }
                }
                //Productos de la tabla
                items(estadoCarrito.productos) { producto ->
                    ElevatedCard(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(bottom = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Row {
                            Column(modifier = Modifier.weight(3f),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {

                                    Image(
                                        painter = BitmapPainter(
                                            Image(producto.first.imagen).asImageBitmap()
                                        ),
                                        contentDescription = "Elemento",
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(70.dp)
                                            .padding(top = 30.dp)
                                    )
                                Text(
                                    text = producto.first.nombre,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Text(
                                text = producto.second.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 95.dp)
                                    .weight(2f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = if (producto.first.rebaja==0) "$${producto.first.precio_normal * producto.second}" else "$${producto.first.precio_normal * producto.second}",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 95.dp)
                                    .weight(2f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable {
                                        viewModel.removeProducto(producto.first, producto.second)
                                    }
                                    .weight(1f)
                                    .padding(top = 95.dp)
                            )
                        }
                    }
                }
                //Total a pagar
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Row {
                            Text(
                                text = "Total:",
                                textAlign = TextAlign.Right,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp)
                                    .weight(3f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "$$total",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp)
                                    .weight(2f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(35.dp))
                }
            }
        }
        //Botón flotante para llevar al usuario a la pantalla de pagos
        FloatingActionButton(
            onClick = {
                navController.navigate("Pagos")
            },
            containerColor = MaterialTheme.colorScheme.primary,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
        ) {
            Text("Proceder al pago",
                textAlign = TextAlign.Center,
                modifier = Modifier

                    .padding(24.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiary)
        }
    }
}


fun extractPaymentId(uri: Uri): String {
    // Extract paymentId from the URI
    // Example: myapp://payment?paymentId=PAYID-12345&token=EC-12345&PayerID=12345
    return uri.getQueryParameter("paymentId") ?: ""
}

fun extractPayerId(uri: Uri): String {
    // Extract payerId from the URI
    // Example: myapp://payment?paymentId=PAYID-12345&token=EC-12345&PayerID=12345
    return uri.getQueryParameter("PayerID") ?: ""
}