
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

/**
 * Reads [file] with bought products
 *
 * @return MutableList: containing all products
 */
fun readFileCompras(file: File): MutableList<Any> {
    // List to be filled with products
    val products:MutableList<Any> = ArrayList()


    csvReader().open(file) {
        readAllWithHeaderAsSequence().forEach { row: Map<String, String> ->
            val product : Any

            val name = row["Nome do produto"].let { if (it?.checkHyphen() != null) it.unaccentUpper() else throw Exception("Product name cant be null")}
            val price_purchase  = row["Preço de compra"].let { if (it?.checkHyphen() != null) it.toFloat() else throw Exception("Product purchase price cant be null")}
            val price_sell = row["Preço de venda"].let { if (it?.checkHyphen() != null) it.toFloat() else throw Exception("Product sell price cant be null")}
            val quantity = row["Quantidade"].let { if (it?.checkHyphen() != null) it.toInt() else throw Exception("Product quantity cant be null")}
            val code = row["Código"].let { if (it?.checkHyphen() != null) it.unaccentUpper() else throw Exception("Product code cant be null")}

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


/**
 * Reads [file] with sold products and changes inventory quantities
 *
 * @return MutableList: inventory modified
 */
fun readFileVendas(file: File, inventory:MutableList<Any>) : MutableList<Any> {
    csvReader().open(file) {
        readAllWithHeaderAsSequence().forEach { row: Map<String, String> ->
            val product = inventory.find {
                it as Product
                it.code == row["Código"]
            } as Product

            product.sell_product(row["Quantidade"]!!.toInt())
        }
    }

    return inventory
}


/**
 * Reads [file] with sequential [inventory] searches.
 *
 * @return List of search index and products quantities
 */
fun readFileBusca(file: File, inventory:MutableList<Any>): MutableList<List<Any>> {
    var index = 0

    val search_results : MutableList<List<Any>> = ArrayList()
    search_results.add(listOf("BUSCAS", "QUANTIDADE"))

    csvReader().open(file) {
        readAllWithHeaderAsSequence().forEach { row: Map<String, String> ->
            var quantity = 0
            index += 1

            // Filter inventory based on the search
            val busca = inventory.filter {
                it as Product

                compareCategory(row["Categoria"], it) && compareType(row["Tipo"], it) && compareSize(row["Tamanho"], it)
                        && compareColors(row["Cor primaria"], row["Cor secundário"], it) && compareVersion(row["Versão"], it)
                        && compareYear(row["Ano de fabricação"], it) && compareMaterial(row["Material de fabricação"], it)
                        && compareRarity(row["Relevância"], it)

            }

            // Count results found
            busca.forEach {
                it as Product
                quantity += it.quantity
            }

            search_results.add(listOf(index, quantity))
        }
    }

    return  search_results
}