package mx.tec.pruebabrillamostodas3.viewmodel

import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import java.util.Calendar
import mx.tec.pruebabrillamostodas3.viewmodel.BTVM

class DatePickerHelper (private val context: Context) {

    fun initializeDatePicker(datePicker: DatePicker): Triple<Int, Int, Int> {
        val today = Calendar.getInstance()
        var selectedDate = Triple(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH))
        datePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH)){
                _, year, month, day ->
            val month = month + 1
            //val msg = "You selected: $day/$month/$year"
            selectedDate = Triple(year, month, day)
            //Aquí abajo se maneja la fecha, ahora mismo muestra una alerta con el Toast
            //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
        return selectedDate
    }
}