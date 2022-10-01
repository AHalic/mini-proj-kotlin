
import java.io.File

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

    var inventory = readFileCompras(compra_file)
//    inventory.forEach{print(it)}
//    println("VENDA")
    inventory = readFileVendas(venda_file, inventory)
//    inventory.forEach{print(it)}
}




