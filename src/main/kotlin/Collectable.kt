class Collectable constructor(val type:TypeCollect, val material:Material, val size:Float, val rarity:Rarity,
                            name:String, price_purchase:Float, price_sell:Float, code:String) :
                            Product(name, price_purchase, price_sell, code, 'C')  {

}