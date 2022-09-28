class Electronic constructor (val type:TypeElectro, val version:Int, val year:java.time.Year,
                              name:String, price_purchase:Float, price_sell:Float, code:String) :
                            Product(name, price_purchase, price_sell, code, 'E') {

}