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

    fun validateUrl(url: String): Boolean {
        return !(validation.findUrl(url)==null)
    }

    fun validateForbiddenWords(text: String): Boolean {
        return !(validation.findForbiddenWords(text)==null)
    }

    fun validateSpecialCharacters(text: String): Boolean {
        return !(validation.findSpecialCharacters(text)==null)
    }
    fun validateJustNumbers(text : String):Boolean{
        return validation.validateJustNumbers(text)

    }


}