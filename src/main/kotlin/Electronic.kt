import java.time.Year

class Electronic constructor (val type:TypeElectro, val version:Int, val year:java.time.Year,
                              name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String) :
                            Product(name, price_purchase, price_sell, quantity, code, 'E') {

    override fun toString(): String {
        return "$code - Electronic called $name of type $type, version $version, year $year\n"
    }
}


/**
 * Function to read electronic information of [row] from _compras file_, and generate
 * an Electronic object, using also products info as: [name], [price_purchase], [price_sell],
 * [quantity] and [code]
 *
 * @return Electronic object
 */
fun read_electronic(name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String, row:Map<String, String>) : Electronic {
    val type = row["Tipo"].let { if (it?.checkHyphen() != null) TypeElectro.valueByType(it!!.unaccentUpper()) else throw Exception("Electronic type cant be null")}
    val version = row["Versão"].let { if (it?.checkHyphen() != null) it!!.toInt() else throw Exception("Electronic version cant be null")}
    val year = row["Ano de frabricação"].let { if (it?.checkHyphen() != null) Year.of(it.toInt()) else throw Exception("Electronic year cant be null")}

    return Electronic(type, version, year, name, price_purchase, price_sell, quantity, code)
}