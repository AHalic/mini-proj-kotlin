import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.text.Normalizer

fun main(args: Array<String>){
    if (args.size != 2){
        throw Exception("Missing Arguments: java -jar miniprojeto.jar <input directory> <output directory>")
    }

    // Declare input and output directories
    val input_directory = args[0]
    val output_directory = args[1]

    val files = File(input_directory).walkTopDown()
    val venda_file = File("$input_directory/vendas.csv")
    val compra_file = File("$input_directory/compras.csv")

    // If vendas and compras files are not in the directory, end the program
    if (!files.contains(venda_file) || !files.contains(compra_file)){
        throw Exception("Missing input files: vendas.csv and compras.csv files necessary!")
    }

    var compras_products = readFile(compra_file)
    println(compras_products)
}


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
 * Reads [file] with bought products
 *
 * @return MutableList: containing all products
 */
fun readFile(file:File): MutableList<Any> {
    // List to be filled with products
    var products:MutableList<Any> = ArrayList()

    csvReader().open(file) {
        readAllWithHeaderAsSequence().forEach { row: Map<String, String> ->
            var product : Any

            val name = row["Nome do produto"].let { if (it?.checkHyphen() != null) it!!.unaccentUpper() else throw Exception("Product name cant be null")}
            val price_purchase  = row["Preço de compra"].let { if (it?.checkHyphen() != null) it!!.toFloat() else throw Exception("Product purchase price cant be null")}
            val price_sell = row["Preço de venda"].let { if (it?.checkHyphen() != null) it!!.toFloat() else throw Exception("Product sell price cant be null")}
            val quantity = row["Quantidade"].let { if (it?.checkHyphen() != null) it!!.toInt() else throw Exception("Product quantity cant be null")}
            val code = row["Código"].let { if (it?.checkHyphen() != null) it!!.unaccentUpper() else throw Exception("Product code cant be null")}

            product = if (row["Categoria"]!!.unaccentUpper() == "ROUPA") {
                read_clothes(name, price_purchase, price_sell, quantity, code, row)
            } else if (row["Categoria"]!!.unaccentUpper() == "COLECIONAVEL") {
                read_collectable(name, price_purchase, price_sell, quantity, code, row)
            } else if (row["Categoria"]!!.unaccentUpper() == "ELETRONICO") {
                read_electronic(name, price_purchase, price_sell, quantity, code, row)
            } else {
                throw Exception("Category must be Clothes, Electronic or Collectable")
            }

            products.add(product)
        }
    }

    return products
}
