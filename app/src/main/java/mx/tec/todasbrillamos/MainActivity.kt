package mx.tec.todasbrillamos

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import mx.tec.todasbrillamos.view.Main
import mx.tec.todasbrillamos.viewmodel.BTVM
import mx.tec.todasbrillamos.viewmodel.PaymentsViewModel
import mx.tec.todasbrillamos.viewmodel.ValidationsVM

/**
 * Pantalla principal de la aplicación donde se muestra información de la organización, asi como
 * la tienda, redes, foros, inicio de sesión, registro, perfil y más.
 *
 * @author Santiago Chevez
 * @author Alan Vega
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param btVM ViewModel principal de la aplicación.
 * @param paymentsVM ViewModel de pagos de la aplicación.
 * @param valVM ViewModel de validaciones de la aplicación.
 * @param flag Bandera que indica si se ha recibido un deep link.
 * @param savedDeepLinkUri Uri del deep link recibido.
 *
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
object PreferencesKeys {
    val username_saved = stringPreferencesKey("user_name")
    val password_saved = stringPreferencesKey("password")
    val hash_password = stringPreferencesKey("hash")
    val user_email    = stringPreferencesKey("email")
    val user_order = stringPreferencesKey("order")
    val user_token = stringPreferencesKey("token")
    val user_id = intPreferencesKey("id")
}

/**
 * Clase principal de la aplicación.
 * Se encarga de manejar la lógica de la aplicación y de inicializar los ViewModels.
 * @constructor Crea un nuevo ViewModel de la aplicación.
 * @param btVM ViewModel principal de la aplicación.
 * @param paymentsVM ViewModel de pagos de la aplicación.
 * @param valVM ViewModel de validaciones de la aplicación.
 * @param flag Bandera que indica si se ha recibido un deep link.
 */

class MainActivity : ComponentActivity() {
    private val btVM : BTVM by viewModels()
    private val paymentsVM: PaymentsViewModel by viewModels()
    private val valVM: ValidationsVM by viewModels()
    var flagS: Boolean = false
    var flagC: Boolean = false

    // Método que se llama cuando la actividad recibe una nueva intención
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent) // Actualiza la intención con la nueva información
    }

    // Método que se llama cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val deepLinkUri: Uri? = intent?.data // Obtiene el URI del deep link de la intención (si existe)
            deepLinkUri?.let { // Si el deep link existe, se guarda en las preferencias compartidas
                saveDeepLinkUri(it)
                println(deepLinkUri)
            }
            val savedDeepLinkUri = getSavedDeepLinkUri() // Recupera el deep link guardado en las preferencias

            if (savedDeepLinkUri != null && savedDeepLinkUri.toString().contains("myapp://payment/success")){
                flagS = true
                flagC = false
                println(savedDeepLinkUri)
            }

            if (savedDeepLinkUri != null && savedDeepLinkUri.toString().contains("myapp://payment/cancel")) { // Si se encuentra un deep link guardado, activa la bandera
                flagS = false
                flagC = true
                println(savedDeepLinkUri)
            }


            // Llama a la función de composición principal pasando los ViewModels y el deep link
            Main(btVM, paymentsVM, flagS, flagC,savedDeepLinkUri, valVM)
        }
    }


    // Método que se llama cuando la actividad pasa al estado de "inicio" (onStart)
    override fun onStart(){
        super.onStart()
        println("Booting")
        println(btVM.getEstadoUsuario())
    }

    // Guarda el URI del deep link en las preferencias compartidas
    private fun saveDeepLinkUri(uri: Uri) {
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("deep_link_uri", uri.toString()).apply()
    }

    // Recupera el URI del deep link desde las preferencias compartidas
    private fun getSavedDeepLinkUri(): Uri? {
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val uriString = sharedPreferences.getString("deep_link_uri", null)
        return uriString?.let { Uri.parse(it) }
    }

}
