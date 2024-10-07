package mx.tec.todasbrillamos.viewmodel

import android.content.Context
import android.widget.DatePicker
import java.util.Calendar

/**
 * Clase que representa un DatePickerHelper en la aplicación para su inicialización y gestión.
 *
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 * @param context Contexto de la aplicación.
 * @constructor Crea un DatePickerHelper con el contexto de la aplicación.
 * @property datePicker DatePicker que se va a inicializar.
 * @property btVM ViewModel de la aplicación.
 * @property selectedDate Fecha seleccionada en el DatePicker.
 *
 */

class DatePickerHelper (private val context: Context) {
    // Función que inicializa el DatePicker y maneja la fecha seleccionada
    fun initializeDatePicker(datePicker: DatePicker, btVM: BTVM) {
        val today = Calendar.getInstance()
        var date = Triple(0,0,0)
        //var selectedDate = Triple(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH))
        datePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH)){
                _, year, month, day ->
            val month = month + 1
            //val msg = "You selected: $day/$month/$year"
            btVM.setFecha(day, month, year)
            //Aquí abajo se maneja la fecha, ahora mismo muestra una alerta con el Toast
            //Toast.makeText(context, selectedDate.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}