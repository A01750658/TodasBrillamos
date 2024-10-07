package mx.tec.todasbrillamos.view

import android.view.ContextThemeWrapper
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import mx.tec.todasbrillamos.R
import mx.tec.todasbrillamos.viewmodel.BTVM
import mx.tec.todasbrillamos.viewmodel.DatePickerHelper
import java.util.Calendar


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
            DatePicker(ContextThemeWrapper(context, R.style.MyDatePickerStyle)).apply {
                datePickerHelper.initializeDatePicker(this, btVM)
                //val selectedDate = datePickerHelper.initializeDatePicker(this, btVM)
                var calendar = Calendar.getInstance()
                calendar.add(Calendar.YEAR, -10)
                maxDate = calendar.timeInMillis
                calendar = Calendar.getInstance()
                calendar.add(Calendar.YEAR, -100)
                minDate = calendar.timeInMillis
            }
        },
        modifier = modifier
    )
}