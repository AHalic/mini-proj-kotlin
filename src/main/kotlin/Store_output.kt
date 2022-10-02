
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

// Custom writer
val writer = csvWriter {
    outputLastLineTerminator = false
}

/**
 * Generates an [inventory] summary file (_estoque_geral.csv_) in the [output] directory
 * containing code,name and quantity of each of the products in it
 */
fun general_inventory(inventory:MutableList<Any>, output:String) {

    writer.open("$output/estoque_geral.csv") {
        // Writes Header
        writeRow("CODIGO", "NOME", "QUANTIDADE")

        // Writes each of the products in the inventory
        inventory.forEach{
            it as Product
            writeRow(it.code, it.name, it.quantity)
        }
    }
}


/**
 * Generates an [inventory] summary file (_estoque_categorias.csv_), by category, in the [output]
 * directory containing category name and its quantities
 */
fun category_inventory(inventory:MutableList<Any>, output:String) {
    var clothes = 0
    var collectable = 0
    var electronic = 0
    val order : MutableList<Char?> = ArrayList()

    // Counts each category quantities
    inventory.forEach{
        it as Product

        if (it.category !in order){
            order.add(it.category)
        }

        when(it.category) {
            'C' -> {
                collectable += it.quantity
            }
            'R' -> {
                clothes += it.quantity
            }
            'E' -> {
                electronic += it.quantity
            }
        }
    }

    writer.open("$output/estoque_categorias.csv") {
        // Writes Header
        writeRow("CATEGORIA", "QUANTIDADE")

        // Writes each of the products categories in the inventory
        order.forEach{
            when (it) {
                'C' -> {
                    writeRow(listOf("COLECIONAVEL", collectable))
                }
                'R' -> {
                    writeRow(listOf("ROUPA", clothes))
                }
                'E' -> {
                    writeRow(listOf("ELETRONICO", electronic))
                }
            }
        }

    }
}


/**
 * Generates the store balance sheet (_balancete.csv_) based on it's [inventory], in the [output]
 * directory containing. The file contains the total amount spent, sold and the profit.
 */
fun balance_sheet(inventory:MutableList<Any>, output:String) {
    var spending = 0f
    var sales = 0f

    // counts amount spend and sold for each product
    inventory.forEach{
        it as Product
        val qnt = it.balance()

        spending += it.price_purchase * qnt.first
        sales += it.price_sell * qnt.second
    }

    writer.open("$output/balancete.csv") {
        writeRows(listOf(listOf("COMPRAS", formatDecimal(spending)),
            listOf("VENDAS", formatDecimal(sales)),
            listOf("BALANCETE", formatDecimal(sales-spending))
        ))
    }
}

/**
 * Generates an [search_results] summary file (_resultado_busca.csv_), in the [output]
 * directory containing search index and the quantities found in the inventory
 */
fun products_search(search_results:MutableList<List<Any>>, output:String) {
    writer.writeAll(search_results, "$output/resultado_busca.csv")
}