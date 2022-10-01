class Clothes constructor(val type:TypeClothes, val size:Size, val color_primary:String, val color_secundary:String?,
                          name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String) :
                          Product(name, price_purchase, price_sell, quantity, code, 'R') {


    override fun toString(): String {
        return "$code - Clothes ($quantity) called $name of type $type, size $size, colors $color_primary and $color_secundary\n"
    }
}


/**
 * Function to read clothes information of [row] from _compras file_, and generate
 * a Clothes object, using also products info as: [name], [price_purchase], [price_sell],
 * [quantity] and [code]
 *
 * @return Clothes object
 */
fun read_clothes(name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String, row:Map<String, String>) : Clothes {
    val type = row["Tipo"].let { if (it?.checkHyphen() != null) TypeClothes.valueOf(it!!.unaccentUpper()) else throw Exception("Clothes type cant be null")}
    val size = row["Tamanho"].let { if (it?.checkHyphen() != null) Size.valueOf(it!!.unaccentUpper()) else throw Exception("Clothes size cant be null")}
    val color_p = row["Cor primaria"].let { if (it?.checkHyphen() != null) it!!.unaccentUpper() else throw Exception("Clothes primary color cant be null")}
    val color_s = row["Cor secundário"].let { if (it?.checkHyphen() != null) it!!.unaccentUpper() else null}

    return Clothes(type, size, color_p, color_s, name, price_purchase, price_sell, quantity, code)
}