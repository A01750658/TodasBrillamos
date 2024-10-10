package mx.tec.todasbrillamos.viewmodel

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
import mx.tec.todasbrillamos.PreferencesKeys
import mx.tec.todasbrillamos.dataStore

/**
 * ViewModel para tratar la parte de los pagos, incluye la integración con Paypal y la gestion de datos
 * del usuario.
 *
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @property apiContext Contexto de la API de Paypal
 * @constructor Crea un objeto de la clase PaymentsViewModel
 * @see APIContext
 * @return Objeto de la clase PaymentsViewModel
 *
 */

class PaymentsViewModel: ViewModel() {
    //Datos de cuenta para conexión con API, para despliegue en producción se tienen que cambiar los valores
    private val apiContext: APIContext = APIContext(
        "AVaA8e03cxh_wVdXiJ4Zz3yfQnq1d4y_0XR-_2V75Er4_YlEKn40AeUGICZZtE68-akwRUPq5L2vK_NI",
        "ELbBJmbOI-qkAQ72da-xOKWOQzcSw-BPylIBST7WUa_m0n8LB97JXx4Vw3JThHow1SO-Vm39XlbsDt6J",
        "sandbox"
    )

    // Función que crea los pagos en Paypal, recibe el monto a pagar como un String
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
                    payment.create(apiContext) // Crea el pago en Paypal
                }

                // Obtiene la URL de aprobación del pago para redirigir al usuario
                val approvalUrl = payment.links.find { it.rel == "approval_url" }?.href
                if (approvalUrl != null) {
                    onSuccess(approvalUrl) // Llama a la función de éxito con la URL de aprobación
                }
                else {
                    onError(Exception("Approval URL not found")) // Llama a la función de error si no se encuentra la URL
                }
            } catch (e: PayPalRESTException) {
                e.printStackTrace()
                onError(e) // Llama a la función de error si hay una excepción
            }
        }
    }

    // Función para ejecutar un pago en paypal después de que el usuario lo aprueba
    fun executePayment(
        paymentId: String,
        payerId: String,
        onSuccess: (Payment) -> Unit,
        onError: (Exception) -> Unit,

    ) {
        viewModelScope.launch {
            try {
                val payment = withContext(Dispatchers.IO) {
                    val payment = Payment().apply { this.id = paymentId }
                    val paymentExecution = PaymentExecution().apply { this.payerId = payerId }
                    payment.execute(apiContext, paymentExecution) // Ejecuta el pago en Paypal
                }
                onSuccess(payment) // Llama a la función de éxito con el pago
            } catch (e: PayPalRESTException) {
                e.printStackTrace()
                onError(e) // Llama a la función de error si hay una excepción
            }
        }
    }

    // Función para guardar los datos del usuario
    fun saveUserData(context: Context, username: String,password: String,email : String,token:String,user_id : Int) {
        //println("Entered saving data")
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[PreferencesKeys.username_saved] = username
                preferences[PreferencesKeys.password_saved] = password
                preferences[PreferencesKeys.user_email] = email
                //println("I AM SAVING DATA: "+token)
                //println("I AM SAVING THE PASSWORD "+password)
                preferences[PreferencesKeys.user_token] = token
                preferences[PreferencesKeys.user_id] = user_id
            }
        }
    }
    fun saveCarritoData(context: Context,btvm: BTVM){
        val estadoCarrito = btvm.estadoCarrito.value
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[PreferencesKeys.user_order] = btvm.createDataInfo(estadoCarrito.productos)

            }
        }
    }
    fun addOrder(context: Context,btvm: BTVM){
        viewModelScope.launch {
            context.dataStore.edit { preferences ->

                val orden = preferences[PreferencesKeys.user_order]
                if (orden != null) {
                    //println("SI tengo orden: ")
                    println(orden)
                    //btvm.addOrder(orden)
                }
            }
        }
    }

    // Función para leer los datos del usuario y hacer login
    fun readUserData(context: Context, btvm : BTVM) {
        viewModelScope.launch {
            context.dataStore.data.collect { preferences ->
                val username = preferences[PreferencesKeys.username_saved]
                val password = preferences[PreferencesKeys.password_saved]
                val email = preferences[PreferencesKeys.user_email]
                val orden = preferences[PreferencesKeys.user_order]
                val token = preferences[PreferencesKeys.user_token]
                val user_id = preferences[PreferencesKeys.user_id]
                //println("THIS IS THE USER TOKEN "+token)
                if (username != null && password != null) {
                    //println("I am about to log in with: ${username} , ${password}")
                    if (orden!=null && token!=null && user_id!=null){
                        //println("I am about to enter order with ${orden}, token = ${token}")
                        btvm.addOrder(orden,token,user_id)
                    }
                    btvm.login(username, password)

                    //println("ORDEN ${orden} | TOKEN ${token} | user_id ${user_id} ")


                }

            }
        }
    }

    fun restoreDataAfterCancel(context: Context, btVM: BTVM){
        viewModelScope.launch {
            context.dataStore.data.collect { preferences ->
                val username = preferences[PreferencesKeys.username_saved]
                val password = preferences[PreferencesKeys.password_saved]
                val email = preferences[PreferencesKeys.user_email]
                val orden = preferences[PreferencesKeys.user_order]
                val token = preferences[PreferencesKeys.user_token]
                val user_id = preferences[PreferencesKeys.user_id]
                //println("THIS IS THE USER TOKEN "+token)
                if (username != null && password != null && orden != null) {
                    btVM.login(username, password)
                    btVM.getProductos()
                    btVM.setCarrito(orden)
                    //println("ORDEN ${orden} | TOKEN ${token} | user_id ${user_id} ")


                }

            }
        }
    }

    // Función para eliminar los datos del usuario
    fun delUserData(context: Context){
        viewModelScope.launch{
            context.dataStore.edit { preferences ->
                //println("I am about to delete the preference keys")
                preferences.remove(PreferencesKeys.username_saved)
                preferences.remove(PreferencesKeys.password_saved)
                preferences.remove(PreferencesKeys.user_email)
                preferences.remove(PreferencesKeys.user_order)
                preferences.remove(PreferencesKeys.user_token)
                preferences.remove(PreferencesKeys.user_id)
            }
        }
    }

}

// Clase que representa la solicitud de pago
data class PaymentRequest(
    val total: String,
    val currency: String,
    val method: String,
    val intent: String,
    val description: String,
    val cancelUrl: String,
    val successUrl: String
)
