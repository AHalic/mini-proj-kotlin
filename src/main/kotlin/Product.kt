open class Product constructor (val name:String, val price_purchase:Float, val price_sell:Float, val quantity:Int) {
    var code:String? = null
    private var category:Char? = null

    constructor(name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String, category:Char): this(name, price_purchase, price_sell, quantity) {
        this.category = category
        this.code = "$category-$code"
    }


}