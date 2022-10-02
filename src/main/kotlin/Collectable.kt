
/**
 * Class representation of the store Collectable Product,
 * this class inherit the [Product] class
 *
 * @property type [TypeCollect] the collectable type
 * @property material [Material] this collectable material
 * @property size this collectable size, can be null
 * @property rarity [Rarity] this collectable rarity
 *
 * @constructor Creates a Collectable with type, size, material, rarity and the [Product] properties
 */
class Collectable constructor(val type:TypeCollect, val material:Material, val size:Float?, val rarity:Rarity,
                            name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String) :
                            Product(name, price_purchase, price_sell, quantity, code, 'C')  {

    override fun toString(): String {
        return "$code - Collectable ($quantity) called $name of type $type, size $size, material $material and rarity $rarity\n"
    }
}


/**
 * Function to read collectable information of [row] from _compras file_, and generate
 * a Collectable object, using also products info as: [name], [price_purchase], [price_sell],
 * [quantity] and [code]
 *
 * @return Collectable object
 */
fun read_collectable(name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String, row:Map<String, String>) : Collectable {
    val type = row["TIPO"].let { if (it?.checkHyphen() != null) TypeCollect.valueOf(it.unaccentUpper()) else throw Exception("Collectable type cant be null")}
    val material = row["MATERIAL DE FABRICACAO"].let { if (it?.checkHyphen() != null) Material.valueOf(it.unaccentUpper()) else throw Exception("Collectable material cant be null")}
    val size = row["TAMANHO"].let { if (it?.checkHyphen() != null) it.toFloat() else null}
    val rarity = row["RELEVANCIA"].let { if (it?.checkHyphen() != null) Rarity.valueOf(it.unaccentUpper()) else throw Exception("Collectable rarity cant be null")}

    return Collectable(type, material, size, rarity, name, price_purchase, price_sell, quantity, code)
}