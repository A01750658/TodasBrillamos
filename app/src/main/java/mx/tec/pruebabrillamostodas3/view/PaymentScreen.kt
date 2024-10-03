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
 * Esta es la pantalla de pagos en donde se podrá pagar los pedidos de la tienda
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
        ElevatedButton(onClick = {
            paymentsViewModel.createPayment(
                total = "10.00",
                currency = "MXN",
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
        }) {
            Text("Pay with PayPal")
        }

        ElevatedButton(onClick = {
                paymentsViewModel.createPayment(
                    total = "10.00",
                    currency = "MXN",
                    method = "credit_card", // Change to "credit_card" for credit card payments
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
