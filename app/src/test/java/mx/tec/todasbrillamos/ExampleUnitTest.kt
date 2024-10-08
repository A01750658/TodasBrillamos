package mx.tec.todasbrillamos

import mx.tec.todasbrillamos.model.Validations
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun validateEmail(){
        val validEmails = listOf(
            "email@gmail.com",
            "firstname.lastname@hotmail.com",
            "email@outlook.com",
            "firstname+lastname@yahoo.com",
            "email@tec.com",
            "1234567890@gmail.com",
            "_______@hotmail.com",
            "firstname-lastname@outlook.com"
        )
        val invalidEmails = listOf(
            "emailgmail.com",
            "firstname.lastnamehotmail.com",
            "emailoutlook.com",
            "firstname.lastnameyahoo.com",
            "emailtec.com",
            "1234567890gmail.com",
            "_______hotmail.com",
            "firstname.lastnameoutlook.com",
            "firstname.lastname@outlook"
        )
        val validations = Validations()

        for (email in validEmails) {
            assertTrue(validations.validateEmail(email))
        }
        for (email in invalidEmails) {
            assertFalse(validations.validateEmail(email))

        }

    }
    @Test
    fun validateJustNumbers(){
        val validations = Validations()
        val justNumbers = listOf(
            "1234567890",
            "0",
            "987654321",
            "12345678901234567890",
            "12345678901234567890123456",
            "5476698",
            "65789608906"
        )
        val notJustNumbers = listOf(
            "1234567890a",
            "a",
            "5363w5thb",
            "46w4hsr",
            "46547y65ejh",
            "t543w65y6thb",
            "345y6hyj5e6"
        )

        for (number in justNumbers) {
            assertTrue(validations.validateJustNumbers(number))
        }
        for (number in notJustNumbers) {
            assertFalse(validations.validateJustNumbers(number))
        }

    }
    @Test
    fun validateFindUrl(){
        val validations = Validations()
        val list_of_strings_with_url = listOf(
            "https://www.google.com",
            "www.google.com",
            "https://www.google.com/search?q=google",
            "Hola a migo    https://www.google.com/search?q=google&oq=google",
            "Soy maligno https://www.google.com/search?q=google&oq=google"
        )
        val list_of_strings_without_url = listOf(
            "Hola a migo",
            "Soy maligno",
            "demoniooooos",
            "Hola",
            "Felicidades")

        for (string in list_of_strings_with_url) {
            assertNotNull(validations.findUrl(string))
        }
        for (string in list_of_strings_without_url) {
            assertNull(validations.findUrl(string))
        }


    }
    @Test
    fun validateFindForbiddenWords(){
        val validations = Validations()
        val list_of_strings_with_forbidden_words = listOf(
            "puta madre egshsthxfttd",
            "puta madre",
            "puta madre hystfhjtyj",
            "puta madre",
            "putito madre",
            "chingadamadreeeeee ")
        val lisr_of_strings_without_forbidden_words = listOf(
            "Hola a migo",
            "Soy maligno",
            "demoniooooos",
            "Hola",
            "Felicidades")

        for (string in list_of_strings_with_forbidden_words) {
            assertNotNull(validations.findForbiddenWords(string))
        }
        for (string in lisr_of_strings_without_forbidden_words) {
            assertNull(validations.findForbiddenWords(string))
        }
        }

    @Test
    fun validatePhoneNumber(){
        val validations = Validations()
        val valid_phone_numbers = listOf(
            "5532532512",
            "0000000000",
            "5544667788",
            "8888888888",
            "4556556565",
            "1111111111",
            "9999999999"
        )
        val invalid_phone_numbers = listOf(
            "55",
            "34546",
            "3546",
            "57657657",
            "654",
            "wenjfhsfdghs<dbfjsdbkf<shfuh"
        )
        for (number in valid_phone_numbers) {
            assertTrue(validations.validatePhoneNumber(number))
        }
        for (number in invalid_phone_numbers) {
            assertFalse(validations.validatePhoneNumber(number))
        }
    }
    @Test
    fun validateSpecialChars(){
        val validations = Validations()
        val valid_special_chars = listOf(
            "@@tgersgrg564",
            "#35436436",
            "@@tgersgrg564",
            "4546567'''5647575765{dflf",
            "@@tgersgrg564",
            "@@tgersgrg564",
            "@@&&&&"
        )
        val invalid_special_chars = listOf(
            "hola",
            "1234567890",
            "como estan"
        )
        for (string in valid_special_chars) {
            assertNotNull(validations.findSpecialCharacters(string))
        }
        for (string in invalid_special_chars) {
            assertNull(validations.findSpecialCharacters(string))
        }
    }
}
