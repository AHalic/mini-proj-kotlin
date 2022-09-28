class Clothes constructor(val type:TypeClothes, val size:Size, val color_primary:String, val color_secundary:String?,
                          name:String, price_purchase:Float, price_sell:Float, code:String) :
                          Product(name, price_purchase, price_sell, code, 'R') {


}