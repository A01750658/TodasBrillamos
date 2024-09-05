package mx.tec.pruebabrillamostodas3.model

import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import java.util.Calendar

class DatePickerHelper (private val context: Context) {

    fun initializeDatePicker(datePicker: DatePicker){
        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH)){
                _, year, month, day ->
            val month = month + 1
            val msg = "You selected: $day/$month/$year"
            //Aquí abajo se maneja la fecha, ahora mismo muestra una alerta con el Toast
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}