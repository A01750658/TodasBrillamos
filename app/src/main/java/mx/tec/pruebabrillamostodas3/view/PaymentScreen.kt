package mx.tec.pruebabrillamostodas3.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.tec.pruebabrillamostodas3.viewmodel.PaymentsViewModel
/**
 * @author Alan Vega
 * Esta es la pantalla de pagos en donde se podrá pagar los pedidos de la tienda
 * @param viewModel Viewmodel de pagos
 */
@Composable
fun PaymentScreen(viewModel: PaymentsViewModel = viewModel()) {
    var paymentStatus by remember { mutableStateOf("Idle") }
    val context = LocalContext.current

    Column {
        Text(text = "Payment Status: $paymentStatus")

        ElevatedButton(onClick = {
            viewModel.createPayment(
                total = "10.00",
                currency = "USD",
                method = "paypal",
                intent = "sale",
                description = "Payment description",
                cancelUrl = "http://localhost:8080/cancel",
                successUrl = "http://localhost:8080/success",
                onSuccess = { approvalUrl ->
                    paymentStatus = "Redirecting to PayPal for approval"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(approvalUrl))
                    context.startActivity(intent)
                },
                onError = { error ->
                    paymentStatus = "Failed to create payment: ${error.message}"
                }
            )
        }) {
            Text("Pay with PayPal")
        }
    }
}