package mx.tec.pruebabrillamostodas3

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import mx.tec.pruebabrillamostodas3.ui.theme.PruebaBrillamosTodas3Theme
import mx.tec.pruebabrillamostodas3.view.Main
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import mx.tec.pruebabrillamostodas3.viewmodel.PaymentsViewModel
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
object PreferencesKeys {
    val username_saved = stringPreferencesKey("user_name")
    val password_saved = stringPreferencesKey("password")
}
class MainActivity : ComponentActivity() {
    private val btVM : BTVM by viewModels()
    private val paymentsVM: PaymentsViewModel by viewModels()
    var flag: Boolean = false

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent) // Update the intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val deepLinkUri: Uri? = intent?.data

            deepLinkUri?.let {
                saveDeepLinkUri(it)
            }

            val savedDeepLinkUri = getSavedDeepLinkUri()

            if (savedDeepLinkUri != null){
                flag = true
            }

            Main(btVM, paymentsVM, flag, savedDeepLinkUri)
        }
    }
    override fun onStart(){
        super.onStart()
        println("aaaaaaaaaaaaaaaaaa Chino message")
        println(btVM.getEstadoUsuario())
    }

    private fun saveDeepLinkUri(uri: Uri) {
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("deep_link_uri", uri.toString()).apply()
    }

    private fun getSavedDeepLinkUri(): Uri? {
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val uriString = sharedPreferences.getString("deep_link_uri", null)
        return uriString?.let { Uri.parse(it) }
    }

    private fun removeDeepLinkUri(){
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("deep_link_uri").apply()
    }

}
