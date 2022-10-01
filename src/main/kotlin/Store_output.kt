
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter


/**
 * Generates an [inventory] summary file (_estoque_geral.csv_) in the [output] directory
 * containing code,name and quantity of each of the products in it
 */
fun general_inventory(inventory:MutableList<Any>, output:String) {

    csvWriter().open("$output/estoque_geral.csv") {
        // Writes Header
        writeRow("CODIGO", "NOME", "QUANTIDADE")

        // Writes each of the products in the inventory
        inventory.forEach{
            val aux = it as Product
            writeRow(aux.code, aux.name, aux.quantity)
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
        val aux = it as Product

        if (aux.category !in order){
            order.add(aux.category)
        }

        when(aux.category) {
            'C' -> {
                collectable += aux.quantity
            }
            'R' -> {
                clothes += aux.quantity
            }
            'E' -> {
                electronic += aux.quantity
            }
        }
    }

    csvWriter().open("$output/estoque_categorias.csv") {
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

    inventory.forEach{
        val aux = it as Product
        val qnt = aux.balance()

        spending += aux.price_purchase * qnt.first
        sales += aux.price_sell * qnt.second
    }

    csvWriter().open("$output/balancete.csv") {
        writeRows(listOf(listOf("COMPRAS", formatDecimal(spending)),
            listOf("VENDAS", formatDecimal(sales)),
            listOf("BALANCETE", formatDecimal(sales-spending))
        ))
    }
}