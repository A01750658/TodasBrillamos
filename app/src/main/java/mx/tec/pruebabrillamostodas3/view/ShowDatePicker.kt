package mx.tec.pruebabrillamostodas3.view

import android.content.Context
import androidx.fragment.app.FragmentActivity
import mx.tec.pruebabrillamostodas3.model.DatePickerFragment

suspend fun showDatePicker(context: Context, onDateSelected: (day: Int, month: Int, year: Int) -> Unit) {
    val activity = context as? FragmentActivity
    if (activity != null) {
        val datePicker = DatePickerFragment(onDateSelected)
        datePicker.show(activity.supportFragmentManager, "datePicker")
    } else {
        throw IllegalArgumentException("Contexto no es una instancia de FragmentActivity")
    }
}
