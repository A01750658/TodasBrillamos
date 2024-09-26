package mx.tec.pruebabrillamostodas3.viewmodel

import androidx.lifecycle.ViewModel
import mx.tec.pruebabrillamostodas3.model.Validations

/**
 * @author Carlos Iker Fuentes Reyes
 * VM para las validaciones de campos
 */
class ValidationsVM : ViewModel() {
    val validation = Validations()

    fun validateEmail(email: String): Boolean {
        return validation.validateEmail(email)
    }

    fun validatePhoneNumber(phoneNumber: String): Boolean {
        return validation.validatePhoneNumber(phoneNumber)
    }

    fun validateUrl(url: String): String? {
        return validation.findUrl(url)
    }

    fun validateForbiddenWords(text: String): String? {
        return validation.findForbiddenWords(text)
    }

    fun validateSpecialCharacters(text: String): String? {
        return validation.findSpecialCharacters(text)
    }


}