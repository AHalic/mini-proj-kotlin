
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>){
    if (args.size != 2){
        throw Exception("Missing Arguments: java -jar miniprojeto.jar <input directory> <output directory>")
    }

    // Declare input and output directories
    val input_directory = args[0]
    val output_directory = args[1]

    val files = if (File(input_directory).isDirectory) File(input_directory).walkTopDown() else throw Exception("Input directory, doesnt exist!")
    val venda_file = File("$input_directory/vendas.csv")
    val compra_file = File("$input_directory/compras.csv")


    try {
        Files.createDirectory(Paths.get(output_directory))
    } catch (_: java.nio.file.FileAlreadyExistsException) {

    }

    // If vendas and compras files are not in the directory, end the program
    if (!files.contains(venda_file) || !files.contains(compra_file)){
        throw Exception("Missing input files: vendas.csv and compras.csv files necessary!")
    }

    var inventory = readFileCompras(compra_file)
    inventory = readFileVendas(venda_file, inventory)

    general_inventory(inventory, output_directory)
    category_inventory(inventory, output_directory)
    balance_sheet(inventory, output_directory)

    val busca_file = File("$input_directory/busca.csv")
    if (files.contains(busca_file)) {
        val search_results = readFileBusca(busca_file, inventory)
        products_search(search_results, output_directory)
    }

}




