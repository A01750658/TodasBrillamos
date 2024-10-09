package mx.tec.todasbrillamos.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import mx.tec.todasbrillamos.viewmodel.BTVM
import mx.tec.todasbrillamos.viewmodel.PaymentsViewModel

/**
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Santiago Chevez
 * @author Cesar Flores
 *
 * @param viewModel Viewmodel de pagos
 * @param paymentsViewModel Viewmodel de pagos
 * @param context Contexto de la aplicación
 * @param savedDeepLinkUriString URI de la dirección profunda guardada
 * @param paymentStatus Estado del pago
 * @param approvalUrl URL de aprobación
 * @param paymentId ID del pago
 * @param payerId ID del pagador
 * @param sharedPreferences Preferencias compartidas
 * @param deepLinkUri URI de la dirección profunda
 * @param showDialog Indicador de diálogo
 */
@Composable
fun PaymentScreen(viewModel: BTVM, paymentsViewModel: PaymentsViewModel = viewModel(), navController: NavHostController) {
    var paymentStatus by remember { mutableStateOf("Idle") } // Estado del pago que indica su progreso, exito o fallo
    var approvalUrl by remember { mutableStateOf<String?>(null) } // URL de aprobación de PayPal
    val context = LocalContext.current // Contexto de la aplicación
    val estadoCarrito by viewModel.estadoCarrito.collectAsState()
    val estadoUsuario by viewModel.estadoUsuario.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    //paymentsViewModel.saveUserData(context,estadoUsuario.correo,estadoUsuario.password,estadoUsuario.correo,estadoUsuario.key,estadoUsuario.id)


    LaunchedEffect(Unit) {
        showDialog = false
    }

    val configuration = LocalConfiguration.current
    val screenOrientation = configuration.orientation

    // Construcción de la dirección de envío del usuario en base a la información disponible
    var direccion = ""
    println(estadoUsuario.direccion.calle)
    println(estadoUsuario.direccion.numero_int.toInt())
    if (estadoUsuario.direccion.calle != "") {
        if (estadoUsuario.direccion.numero_int == "" || estadoUsuario.direccion.numero_int.toInt() == -1 || estadoUsuario.direccion.numero_int.toInt() == 0){
            direccion="${estadoUsuario.direccion.calle} #${estadoUsuario.direccion.numero_exterior}, ${estadoUsuario.direccion.colonia}, ${estadoUsuario.direccion.municipio}, ${estadoUsuario.direccion.estado}, C.P. ${estadoUsuario.direccion.cp}"
        }
        else{
            direccion="${estadoUsuario.direccion.calle} #${estadoUsuario.direccion.numero_exterior}, Int ${estadoUsuario.direccion.numero_int}, ${estadoUsuario.direccion.colonia}, ${estadoUsuario.direccion.municipio}, ${estadoUsuario.direccion.estado}, C.P. ${estadoUsuario.direccion.cp}"
        }

    }
    else{ direccion = ""}

    val total: Float = estadoCarrito.total
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val savedDeepLinkUriString = sharedPreferences.getString("deep_link_uri", null)
    val deepLinkUri = savedDeepLinkUriString?.let { Uri.parse(it) }

    // Efecto lanzado que se ejecuta cuando hay un deep link URI, procesando el pago en base a ese URI
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
                        showDialog = true
                        println(paymentStatus)
                        // Clear the deep link data to prevent re-execution
                        (context as? ComponentActivity)?.intent?.data = null
                        sharedPreferences.edit().remove("deep_link_uri").apply() // Limpia el deep link para evitar la reejecución del pago
                        // Lee los datos del usuario actualizados tras el pago y luego los elimina

                        paymentsViewModel.readUserData(context, viewModel)
                        //paymentsViewModel.addOrder(context, viewModel)
                        paymentsViewModel.delUserData(context)
                    },
                    onError = { error ->
                        paymentStatus = "Failed to execute payment: ${error.message}"
                        showDialog = true
                        sharedPreferences.edit().remove("deep_link_uri").apply()
                        println(paymentStatus)
                    }
                )
            }
        }
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary)) {
            Spacer(modifier = Modifier.height(16.dp))
            Titulo("Resumen de la Orden", color = MaterialTheme.colorScheme.onTertiary)
            // Muestra la dirección de envío del usuario para editar
            Spacer(modifier = Modifier.height(16.dp))
            Etiqueta(texto = "Dirección de envio")
            // Muestra la dirección de envío del usuario para editar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.onTertiary)
            ) {
                Text( // Texto de la dirección
                    text = direccion,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(vertical = 20.dp)
                        .weight(5f),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    maxLines = 5,
                    fontSize = if (screenOrientation == 1) 15.sp else 25.sp
                )
                ElevatedButton( // Botón para editar la dirección
                    onClick = {
                        viewModel.copiarDireccion() // Copia la dirección del viewmodel
                        navController.navigate(Pantallas.RUTA_EDITAR_DIRECCION)
                    }, // Navega a la pantalla de edición de dirección
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 25.dp)
                        .padding(horizontal = 10.dp)
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
            }
            Etiqueta(texto = "Productos")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.onTertiary)
            ) {
                for (producto in estadoCarrito.productos) {
                    Text(
                        " ${producto.second} | ${producto.first.nombre}",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Left,
                        color = Color.Black,
                        maxLines = 5,
                        fontSize = if (screenOrientation == 1) 15.sp else 25.sp,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 2.dp)
                            .fillMaxWidth()
                    )
                }
            }
            Subtitulo(
                text = "Total a pagar",
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 20
            )
            Subtitulo(
                text = "$${estadoCarrito.total}",
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 20
            )
            HorizontalDivider()
            Subtitulo(
                text = "Pagar con ...",
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 20
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                ElevatedButton(onClick = { // Botón para realizar el pago con PayPal
                    paymentsViewModel.createPayment(
                        total = "${total}", // Monto total del pago
                        currency = "MXN", // Moneda
                        method = "paypal", // Método de pago (PayPal)
                        intent = "sale", // Tipo de transacción (Venta)
                        description = "Payment description", // Descripción del pago
                        cancelUrl = "http://localhost:8080/cancel", // URL de cancelación
                        successUrl = "myapp://payment", // URL de éxito
                        onSuccess = { url ->
                            approvalUrl = url
                            println(approvalUrl)
                            println(viewModel.getEstadoUsuario())
                            paymentStatus = "Redirecting to PayPal for approval"
                            println(paymentStatus)
                            paymentsViewModel.saveCarritoData(context, viewModel)
                            // Redirige a la página de PayPal para la aprobación
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                            println("Step after page complete, I guess, I think its supposed to show after you get back to the app if everything is gucci")
                        },
                        onError = { error ->
                            paymentStatus = "Failed to create payment: ${error.message}"
                        }
                    )
                },
                    modifier = Modifier.align(Alignment.Center),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text("Pagar con PayPal", style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = true },
                title = {
                    if (paymentStatus.contains("successful")) {
                        Text(
                            text = "¡Orden Completa!",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = "¡Orden Fallida!",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Text(
                        text = if (paymentStatus.contains("successful")) {
                            "Tu pago se ha procesado exitosamente. Gracias por tu compra."
                        } else {
                            "Hubo un problema con el pago: $paymentStatus"
                        }
                    )
                },
                confirmButton = {
                    // Centrar el botón usando un Box
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = androidx.compose.ui.Alignment.Center  // Centra el contenido
                    ) {
                        Button(onClick = {
                            showDialog = false
                            navController.navigate(Pantallas.RUTA_APP_HOME)
                        }) {
                            Text("Aceptar", color = MaterialTheme.colorScheme.onTertiary)
                        }
                    }
                }
            )


        }
    }
}

/*
fun extractPaymentId(uri: Uri): String {
    // Extract paymentId from the URI
    // Example: myapp://payment?paymentId=PAYID-12345&token=EC-12345&PayerID=12345
    return uri.getQueryParameter("paymentId") ?: ""
}

fun extractPayerId(uri: Uri): String {
    // Extract payerId from the URI
    // Example: myapp://payment?paymentId=PAYID-12345&token=EC-12345&PayerID=12345
    return uri.getQueryParameter("PayerID") ?: ""
}*/