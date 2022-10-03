import java.time.Year


/**
 * Class representation of the store Eletronic Product,
 * this class inherit the [Product] class
 *
 * @property type [TypeElectro] the electronic type
 * @property version this electronic version
 * @property year this electronic year of release
 *
 * @constructor Creates an Electronic with type, version, year and the [Product] properties
 */
class Electronic constructor (val type:TypeElectro, val version:Int, val year: Year,
                              name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String) :
                              Product(name, price_purchase, price_sell, quantity, code, 'E') {

    override fun toString(): String {
        return "$code - Electronic ($quantity) called $name of type $type, version $version, year $year\n"
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
    val type = row["TIPO"].let { if (it?.checkHyphen() != null) TypeElectro.valueByType(it.unaccentUpper()) else throw Exception("Electronic type cant be null")}
    val version = row["VERSAO"].let { if (it?.checkHyphen() != null) it.toInt() else throw Exception("Electronic version cant be null")}
    val year = row["ANO DE FABRICACAO"].let { if (it?.checkHyphen() != null) Year.of(it.toInt()) else throw Exception("Electronic year cant be null")}

    return Electronic(type, version, year, name, price_purchase, price_sell, quantity, code)
}