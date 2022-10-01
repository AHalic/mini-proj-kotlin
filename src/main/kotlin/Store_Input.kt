import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

/**
 * Reads [file] with bought products
 *
 * @return MutableList: containing all products
 */
fun readFileCompras(file: File): MutableList<Any> {
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


fun readFileVendas(file: File, inventory:MutableList<Any>) : MutableList<Any> {
    csvReader().open(file) {
        readAllWithHeaderAsSequence().forEach { row: Map<String, String> ->
            var product = inventory.find {
                var aux = it as Product
                aux.code == row["Código"]
            } as Product

            product.sell_product(row["Quantidade"]!!.toInt())
        }
    }

    return inventory
}