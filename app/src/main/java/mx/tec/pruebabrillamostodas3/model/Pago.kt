package mx.tec.pruebabrillamostodas3.model

import com.paypal.api.payments.*
import com.paypal.base.rest.APIContext
import com.paypal.base.rest.PayPalRESTException


fun Pago(){
    val paymentService = Payments(PayPalConfig.apiContext)

    // Create a payment
    val payment = paymentService.createPayment(
        total = "10.00",
        currency = "USD",
        method = "paypal",
        intent = "sale",
        description = "Payment description",
        cancelUrl = "http://localhost:8080/cancel",
        successUrl = "http://localhost:8080/success"
    )

    if (payment != null) {
        println("Payment created successfully: ${payment.id}")
        // Redirect the user to the approval URL
        val approvalUrl = payment.links.find { it.rel == "approval_url" }?.href
        println("Approval URL: $approvalUrl")
        // Redirect the user to the approval URL (this would be done in a web application)
    } else {
        println("Failed to create payment")
    }

    val executedPayment = paymentService.executePayment(paymentId = "PAYMENT_ID", payerId = "PAYER_ID")
    if (executedPayment != null) {
        println("Payment executed successfully: ${executedPayment.id}")
    } else {
        println("Failed to execute payment")
    }
}
