package mx.tec.pruebabrillamostodas3.view

import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM
import mx.tec.pruebabrillamostodas3.viewmodel.DatePickerHelper



@Composable
fun DatePickerScreen(btVM: BTVM, modifier: Modifier = Modifier){
    val context = LocalContext.current
    val datePickerHelper = DatePickerHelper(context)

    AndroidView(
        factory = { context ->
            DatePicker(context).apply {
                //datePickerHelper.initializeDatePicker(this)
                val selectedDate = datePickerHelper.initializeDatePicker(this)
                val (year, month, day) = selectedDate
                btVM.setFecha(year, month, day)
            }
        },
        modifier = modifier
    )
}