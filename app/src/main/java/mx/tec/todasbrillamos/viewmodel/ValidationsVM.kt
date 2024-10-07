package mx.tec.todasbrillamos.viewmodel

import androidx.lifecycle.ViewModel
import mx.tec.todasbrillamos.model.Validations

/**
 * ViewModel para las validaciones de campos
 *
 * @author Carlos Iker Fuentes Reyes
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Cesar Flores
 *
 * @property validation Objeto de la clase Validations
 * @constructor Crea un objeto de la clase Validations
 * @see Validations
 * @return Objeto de la clase Validations
 *
 */

class ValidationsVM : ViewModel() {
    // Instancia del objeto de la clase Validations
    val validation = Validations()

    // Funciones de validación del correo electrónico
    fun validateEmail(email: String): Boolean {
        return validation.validateEmail(email)
    }

    // Funciones de validación del número de teléfono
    fun validatePhoneNumber(phoneNumber: String): Boolean {
        return validation.validatePhoneNumber(phoneNumber)
    }

    // Funciones de validación de la url
    fun validateUrl(url: String): Boolean {
        return !(validation.findUrl(url)==null)
    }

    // Funciones de validación si el texto contiene palabras prohibidas
    fun validateForbiddenWords(text: String): Boolean {
        return !(validation.findForbiddenWords(text)==null)
    }

    // Funciones de validación si el texto contiene caracteres especiales
    fun validateSpecialCharacters(text: String): Boolean {
        return !(validation.findSpecialCharacters(text)==null)
    }

    // Funciones de validación si el texto contiene solo números
    fun validateJustNumbers(text : String):Boolean{
        return validation.validateJustNumbers(text)

    }

}