package mx.tec.todasbrillamos.model

class Validations {
    //verify email
    val email_regex = Regex(".*@[gmail|hotmail|outlook|yahoo|tec]+\\.com")
    //verify url
    val url_regex = Regex("([http|https]+://.+)|(www.+)")
    //verify phone number
    val phone_number_regex = Regex("[0-9]{10}")
    //verify forbidden words
    val forbidden_words_regex = Regex(".*putit[oa]s?|put[oa@]|chingada|pendej[ao]|maldit[oa]|nigga|terrorismo.*|mierd[ao]|cagad[oa]|nigg(a|e|er|ers)|pinche|mamahuevo|pendej(ada|adas|p)|pit[oa]|mamapinga|verg[oa@]|culo|culiado|odio|cracker|slope|come perros| mam(ón|on|ona|one|ones)|pinch([e3]s|a)|ching(a|ado|ada) |perr[oa] |miado |pene*|pit(o|os)|an(o|al|ales)")
    //find special characters
    val number_special_chars_regex = Regex("[^a-zA-Z0-9\\s]+|[áéíóúÁÉÍÓÚ]+")
    val just_numbers_regex = Regex("\\d*")

    /**
     * función que valida si una entrada de strings son sólo numeros
     * @param nums []String]
     * @return [Boolean]
     *
     */
    fun validateJustNumbers(nums: String) :Boolean{
        return just_numbers_regex.matches(nums)
    }
    /**
     * Función que valida si un string es una url
     * @param email String
     * @return [Boolean]
     */
    fun validateEmail(email: String): Boolean {
        return email_regex.matches(email)
    }

    /**
     * Función que valida si un string es una url
     * @param url String
     * @return String?
     */
    fun findUrl(url: String): String? {
        return url_regex.find(url)?.value
    }

    /**
     * Función que valida si un string es un número de teléfono
     * @param phoneNumber String
     * @return [Boolean]
     */
    fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phone_number_regex.matches(phoneNumber)
    }

    /**
     * Función que valida si un string contiene palabras prohibidas
     * @param text String
     * @return String?
     */
    fun findForbiddenWords(text: String): String? {
        return forbidden_words_regex.find(text)?.value
    }

    /**
     * Función que valida si un string contiene un email
     * @param text String
     * @return String?
     */
    fun findEmail(text: String): String? {
        return email_regex.find(text)?.value
    }

    /**
     * Función que valida si un string contiene un número de teléfono
     * @param text String
     * @return String?
     */
    fun findPhoneNumber(text: String): String? {
        return phone_number_regex.find(text)?.value
    }

    /**
     * Función que valida si un string contiene caracteres especiales
     * @param text String
     * @return String?
     */
    fun findSpecialCharacters(text: String): String? {
        return number_special_chars_regex.find(text)?.value
    }
}

fun main(){
    val validation = Validations()
    println(validation.email_regex.matches("james.madison@oulook.com"))
    println(validation.url_regex.matches("https://www.google.com"))
    println(validation.url_regex.matches("www.google.com"))
    println(validation.phone_number_regex.matches("+525532532512"))
    println(validation.phone_number_regex.matches("5532532512"))
    println(validation.forbidden_words_regex.find("puta madre"))
   if(validation.forbidden_words_regex.find("terrorismo") != null){
       println("Encontrado")
   }

    if(validation.number_special_chars_regex.find("AA677(((AA")!=null){
       println("AAAA")
    }


}