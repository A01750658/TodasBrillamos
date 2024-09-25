package mx.tec.pruebabrillamostodas3.model

class Validations {
    val email_regex = Regex(".*@[gmail|hotmail|outlook|yahoo]+\\.com")
    val url_regex = Regex("([http|https]+://.+)|(www.+)")
    val phone_number_regex = Regex("(\\+?\\d{2})?[0-9]{10}")
    val forbidden_words_regex = Regex(".*putit[oa]s?|put[oa@]|chingada|pendej[ao]|maldit[oa]|terrorismo|sexo|sexo.*")

    fun validateEmail(email: String): Boolean {
        return email_regex.matches(email)
    }
    fun findUrl(url: String): String? {
        return url_regex.find(url)?.value
    }
    fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phone_number_regex.matches(phoneNumber)
    }
    fun findForbiddenWords(text: String): String? {
        return forbidden_words_regex.find(text)?.value
    }
    fun findEmail(text: String): String? {
        return email_regex.find(text)?.value
    }
    fun findPhoneNumber(text: String): String? {
        return phone_number_regex.find(text)?.value
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

    println(validation.findForbiddenWords("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.\n puto"))

    println(validation.findPhoneNumber("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.\n 5532532512"))

}