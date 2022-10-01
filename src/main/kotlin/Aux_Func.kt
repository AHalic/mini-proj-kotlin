import java.text.DecimalFormat
import java.text.Normalizer
import java.text.NumberFormat
import java.util.*

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

/**
 * Auxiliary function to format float [value] according to the following rules:
 * - minimum 0 fraction digits
 * - maximum 2 fraction digits
 * - no group separator
 * - dot ('.') as decimal separator
 *
 *      __example__: 10250.3
 *
 * @return String
 */
fun formatDecimal(value: Float) : String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.maximumFractionDigits = 2
    numberFormat.minimumFractionDigits = 0

    if (numberFormat is DecimalFormat) {
        numberFormat.isDecimalSeparatorAlwaysShown = false
        numberFormat.isGroupingUsed = false
    }

    return numberFormat.format(value)
}