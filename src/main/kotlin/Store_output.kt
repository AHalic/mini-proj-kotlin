import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

/**
 * Generates an [inventory] summary file in the [output] directory containing
 * code,name and quantity of each of the products in it
 */
fun general_inventory(inventory:MutableList<Any>, output:String) {

    csvWriter().open("$output/estoque_geral.csv") {
        // Writes Header
        writeRow("CODIGO", "NOME", "QUANTIDADE")

        // Writes each of the products in the inventory
        inventory.forEach{
            var aux = it as Product
            writeRow(aux.code, aux.name, aux.quantity)
        }
    }
}


/**
 * Generates an [inventory] summary file, by category, in the [output] directory containing
 * category name and its quantities
 */
fun category_inventory(inventory:MutableList<Any>, output:String) {
    var clothes = 0
    var collectable = 0
    var electronic = 0

    // Counts each category quantities
    inventory.forEach{
        var aux = it as Product

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
        writeRows(listOf(listOf("ROUPA", clothes),
                         listOf("COLECIONAVEL", collectable),
                         listOf("ELETRONICO", electronic)
        ))
    }
}