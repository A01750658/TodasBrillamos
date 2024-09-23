package mx.tec.pruebabrillamostodas3.model

/**
 * @author Alan Rodrigo Vega Reza
 */

import com.paypal.api.payments.*
import com.paypal.base.rest.APIContext
import com.paypal.base.rest.PayPalRESTException

class Payments(private val apiContext: APIContext){

fun createPayment(total: String, currency: String, method: String, intent: String, description: String, cancelUrl: String, successUrl: String): Payment? {
    val payment = Payment().apply {
        this.intent = intent
        payer = Payer().apply { this.paymentMethod = method }
        transactions = listOf(Transaction().apply {
            this.amount = Amount().apply {
                this.currency = currency
                this.total = total
            }
            this.description = description
        })
        redirectUrls = RedirectUrls().apply {
            this.cancelUrl = cancelUrl
            this.returnUrl = successUrl
        }
    }

    return try {
        payment.create(apiContext)
    } catch (e: PayPalRESTException) {
        e.printStackTrace()
        null
    }
}

fun executePayment(paymentId: String, payerId: String): Payment? {
    val payment = Payment().apply { this.id = paymentId }
    val paymentExecution = PaymentExecution().apply { this.payerId = payerId }

    return try {
        payment.execute(apiContext, paymentExecution)
    } catch (e: PayPalRESTException) {
        e.printStackTrace()
        null
    }
}
}

fun main() {
    val payPalService = Payments(PayPalConfig.apiContext)

    // Create a payment
    val payment = payPalService.createPayment(
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
}

