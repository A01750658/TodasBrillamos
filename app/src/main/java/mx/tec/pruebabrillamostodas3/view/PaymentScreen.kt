package mx.tec.pruebabrillamostodas3.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import mx.tec.pruebabrillamostodas3.viewmodel.PaymentsViewModel

/**
 * Pantalla de pagos en la que los usuarios pueden realizar el pago de sus pedidos a través de PayPal o tarjeta de crédito.
 * La pantalla permite la creación de pagos, redirige a la página de PayPal o similar para la aprobación,
 * y luego maneja la ejecución del pago cuando se vuelve a la aplicación.
 *
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
 *
 */

@Composable
fun PaymentScreen(viewModel: BTVM, paymentsViewModel: PaymentsViewModel = viewModel()) {
    var paymentStatus by remember { mutableStateOf("Idle") } // Estado del pago que indica su progreso, exito o fallo
    var approvalUrl by remember { mutableStateOf<String?>(null) } // URL de aprobación de PayPal
    val context = LocalContext.current // Contexto de la aplicación

    //Accede a las preferencias compartidas de la aplicación para obtener la URI de la dirección profunda guardada
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
                        println(paymentStatus)
                        // Clear the deep link data to prevent re-execution
                        //(context as? ComponentActivity)?.intent?.data = null
                        sharedPreferences.edit().remove("deep_link_uri").apply() // Limpia el deep link para evitar la reejecución del pago

                        // Lee los datos del usuario actualizados tras el pago y luego los elimina
                        paymentsViewModel.readUserData(context, viewModel)
                        paymentsViewModel.delUserData(context)
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

    Column {
        ElevatedButton(onClick = { // Botón para realizar el pago con PayPal
            paymentsViewModel.createPayment(
                total = "10.00", // Monto total del pago
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

                    // Redirige a la página de PayPal para la aprobación
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                    println("Step after page complete, I guess, I think its supposed to show after you get back to the app if everything is gucci")
                },
                onError = { error ->
                    paymentStatus = "Failed to create payment: ${error.message}"
                }
            )
        }) {
            Text("Pay with PayPal")
        }

        // Botón para realizar el pago con tarjeta de crédito
        ElevatedButton(onClick = {
                paymentsViewModel.createPayment(
                    total = "10.00", // Monto total del pago
                    currency = "MXN", // Moneda
                    method = "credit_card", // Cambio a tarjeta de crédito
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

                        // Redirige a la página de Pago con tarjeta de crédito para la aprobación
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                        println("Step after page complete, I guess, I think its supposed to show after you get back to the app if everything is gucci")
                    },
                    onError = { error ->
                        paymentStatus = "Failed to create payment: ${error.message}"
                    }
                )

        }){

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
