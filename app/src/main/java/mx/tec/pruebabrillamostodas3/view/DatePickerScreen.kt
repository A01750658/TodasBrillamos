package mx.tec.pruebabrillamostodas3.view

import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import mx.tec.pruebabrillamostodas3.model.DatePickerHelper

@Composable
fun DatePickerScreen(modifier: Modifier = Modifier){
    val context = LocalContext.current
    val datePickerHelper = DatePickerHelper(context)

    AndroidView(
        factory = { context ->
            DatePicker(context).apply {
                datePickerHelper.initializeDatePicker(this)
            }
        },
        modifier = modifier
    )
}