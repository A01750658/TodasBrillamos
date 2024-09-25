package mx.tec.pruebabrillamostodas3.viewmodel

import android.content.Context
import android.net.Uri
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paypal.base.rest.APIContext
import com.paypal.base.rest.PayPalRESTException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.paypal.api.payments.*
import mx.tec.pruebabrillamostodas3.PreferencesKeys
import mx.tec.pruebabrillamostodas3.dataStore

/**
 * @author Alan Vega
 */

class PaymentsViewModel: ViewModel() {

    private val apiContext: APIContext = APIContext(
        "AVaA8e03cxh_wVdXiJ4Zz3yfQnq1d4y_0XR-_2V75Er4_YlEKn40AeUGICZZtE68-akwRUPq5L2vK_NI",
        "ELbBJmbOI-qkAQ72da-xOKWOQzcSw-BPylIBST7WUa_m0n8LB97JXx4Vw3JThHow1SO-Vm39XlbsDt6J",
        "sandbox"
    )

    fun createPayment(
        total: String,
        currency: String,
        method: String,
        intent: String,
        description: String,
        cancelUrl: String,
        successUrl: String,
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val payment = withContext(Dispatchers.IO) {
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
                    payment.create(apiContext)
                }
                val approvalUrl = payment.links.find { it.rel == "approval_url" }?.href
                if (approvalUrl != null) {
                    onSuccess(approvalUrl)
                } else {
                    onError(Exception("Approval URL not found"))
                }
            } catch (e: PayPalRESTException) {
                e.printStackTrace()
                onError(e)
            }
        }
    }

    fun executePayment(
        paymentId: String,
        payerId: String,
        onSuccess: (Payment) -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val payment = withContext(Dispatchers.IO) {
                    val payment = Payment().apply { this.id = paymentId }
                    val paymentExecution = PaymentExecution().apply { this.payerId = payerId }
                    payment.execute(apiContext, paymentExecution)
                }
                onSuccess(payment)
            } catch (e: PayPalRESTException) {
                e.printStackTrace()
                onError(e)
            }
        }
    }
    fun saveUserData(context: Context, username: String,password: String) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[PreferencesKeys.username_saved] = username
                preferences[PreferencesKeys.password_saved] = password
            }
        }
    }
    fun readUserData(context: Context, btvm : BTVM = BTVM()) {
        viewModelScope.launch {
            context.dataStore.data.collect { preferences ->
                val username = preferences[PreferencesKeys.username_saved]
                val password = preferences[PreferencesKeys.password_saved]
                if (username != null && password != null) {
                    btvm.login(username, password);
                }

            }
        }
    }

}
