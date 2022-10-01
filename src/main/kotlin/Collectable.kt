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
    val type = row["Tipo"].let { if (it?.checkHyphen() != null) TypeCollect.valueOf(it.unaccentUpper()) else throw Exception("Collectable type cant be null")}
    val material = row["Material de fabricação"].let { if (it?.checkHyphen() != null) Material.valueOf(it.unaccentUpper()) else throw Exception("Collectable material cant be null")}
    val size = row["Tamanho"].let { if (it?.checkHyphen() != null) it.toFloat() else null}
    val rarity = row["Relevância"].let { if (it?.checkHyphen() != null) Rarity.valueOf(it.unaccentUpper()) else throw Exception("Collectable rarity cant be null")}

    return Collectable(type, material, size, rarity, name, price_purchase, price_sell, quantity, code)
}