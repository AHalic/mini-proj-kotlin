class Collectable constructor(val type:TypeCollect, val material:Material, val size:Float, val rarity:Rarity,
                            name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String) :
                            Product(name, price_purchase, price_sell, quantity, code, 'C')  {

}