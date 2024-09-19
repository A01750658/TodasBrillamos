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
