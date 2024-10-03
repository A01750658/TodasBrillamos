package mx.tec.pruebabrillamostodas3.view

import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import mx.tec.pruebabrillamostodas3.viewmodel.DatePickerHelper


/**
 * Calendario para seleccionar fecha de nacimiento al registrarse
 * @author Cesar Flores
 * @param btVM ViewModel de la aplicación
 * @param modifier modificador de la función
 */
@Composable
fun DatePickerScreen(btVM: BTVM, modifier: Modifier = Modifier){
    val context = LocalContext.current
    val datePickerHelper = DatePickerHelper(context)

    AndroidView(
        factory = { context ->
            DatePicker(context).apply {
                datePickerHelper.initializeDatePicker(this, btVM)
                //val selectedDate = datePickerHelper.initializeDatePicker(this, btVM)
            }
        },
        modifier = modifier
    )
}