package mx.tec.pruebabrillamostodas3

import android.content.Intent
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
import androidx.compose.ui.tooling.preview.Preview
import mx.tec.pruebabrillamostodas3.ui.theme.PruebaBrillamosTodas3Theme
import mx.tec.pruebabrillamostodas3.view.Main
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import mx.tec.pruebabrillamostodas3.viewmodel.PaymentsViewModel

class MainActivity : ComponentActivity() {
    private val btVM : BTVM by viewModels()
    private val paymentsVM: PaymentsViewModel by viewModels()

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent) // Update the intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main(btVM, paymentsVM)
        }
    }
    override fun onStart(){
        super.onStart()
        println("aaaaaaaaaaaaaaaaaa Chino message")
        println(btVM.getEstadoUsuario())
    }

}

