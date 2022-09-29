import java.io.File


fun main(args: Array<String>){
    if (args.size != 2){
        throw Exception("Missing Arguments: java -jar miniprojeto.jar <input directory> <output directory>")
    }

    // Declare input and output directories
    val input_directory = args[0]
    val output_directory = args[1]

    val files = File(input_directory).walkTopDown()
    if (!files.contains(File("$input_directory/vendas.csv")) ||
        !files.contains(File("$input_directory/compras.csv"))){
        throw Exception("Missing input files: vendas.csv and compras.csv files necessary!")
    }
}
