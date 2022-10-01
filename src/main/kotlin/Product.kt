open class Product constructor (var name:String, var price_purchase:Float, var price_sell:Float, var quantity:Int) {
    var code:String? = null
    private var category:Char? = null

    constructor(name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String, category:Char): this(name, price_purchase, price_sell, quantity) {
        this.category = category
        this.code = "$category-$code"
    }

    open fun sell_product(quantity_sold: Int) {
        this.quantity -= quantity_sold
    }

}