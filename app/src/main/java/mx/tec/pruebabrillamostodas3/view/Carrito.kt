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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.paypal.base.rest.APIContext

@Composable
fun Carrito(viewModel: BTVM, paymentsViewModel: PaymentsViewModel, deepLinkUri: Uri?){

    val estadoCarrito by viewModel.estadoCarrito.collectAsState()

    var paymentStatus by remember { mutableStateOf("Idle") }
    var approvalUrl by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val savedDeepLinkUriString = sharedPreferences.getString("deep_link_uri", null)
    val deepLinkUri = savedDeepLinkUriString?.let { Uri.parse(it) }

    LaunchedEffect(deepLinkUri) {
        println("Shit entered the LaunchedEffect")
        deepLinkUri?.let { uri ->
            val paymentId = extractPaymentId(uri)
            val payerId = extractPayerId(uri)
            if (paymentId.isNotEmpty() && payerId.isNotEmpty()) {
                paymentsViewModel.executePayment(
                    paymentId = paymentId,
                    payerId = payerId,
                    onSuccess = { payment ->
                        paymentStatus = "Payment successful: ${payment.id}"
                        println(paymentStatus)
                        // Clear the deep link data to prevent re-execution
                        //(context as? ComponentActivity)?.intent?.data = null
                        sharedPreferences.edit().remove("deep_link_uri").apply()
                    },
                    onError = { error ->
                        paymentStatus = "Failed to execute payment: ${error.message}"
                        sharedPreferences.edit().remove("deep_link_uri").apply()
                        println(paymentStatus)
                    }
                )
            }
        }
    }

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
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primaryContainer,
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
                            text = "$9999.99",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f)
                        )
                    }
                }
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
                                text = "Productos",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp)
                                    .weight(3f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Precio",
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
                            Text(
                                text = "Producto ${producto.first}",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp)
                                    .weight(3f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Precio ${producto.second}",
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
                                text = "$9999.99",
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

        FloatingActionButton(
            onClick = {
                paymentsViewModel.createPayment(
                    total = "10.00",
                    currency = "USD",
                    method = "paypal",
                    intent = "sale",
                    description = "Payment description",
                    cancelUrl = "http://localhost:8080/cancel",
                    successUrl = "myapp://payment",
                    onSuccess = { url ->
                        approvalUrl = url
                        println(approvalUrl)
                        println(viewModel.getEstadoUsuario())
                        paymentStatus = "Redirecting to PayPal for approval"
                        println(paymentStatus)
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                        println("Step after page complete, I guess, I think its supposed to show after you get back to the app if everything is gucci")
                    },
                    onError = { error ->
                        paymentStatus = "Failed to create payment: ${error.message}"
                    }
                )
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
