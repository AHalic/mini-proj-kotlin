import java.text.Normalizer

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()


/**
 * String method to ignore word's accents and make it uppercase
 *
 *      example: Fabricação == FABRICACAO
 */
fun CharSequence.unaccentUpper(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "").uppercase()
}


/**
 * String method that check if it is a hyphen, if so then it should be null
 *
 * @return String?
 */
fun String.checkHyphen() : String? {
    return if (this != "-") this else null
}