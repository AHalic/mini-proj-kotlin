/**
 * Class representation of the store Product
 *
 * @property name the name of this product
 * @property price_purchase the price this product was bought
 * @property price_sell the price this product can be sold
 * @property quantity the quantity of this product in the store's inventory
 * @property code the code representing this product
 * @property category a char representing this product class ('R', 'C' or 'E')
 * @property bought quantity of products purchased
 * @property sold quantity of products sold
 *
 *
 * @constructor Creates a Product with name, purchase and sell price and quantity
 */
open class Product constructor (var name:String, var price_purchase:Float, var price_sell:Float, var quantity:Int) {
    var code:String? = null
    var category:Char? = null
    private var bought = 0
    private var sold = 0

    constructor(name:String, price_purchase:Float, price_sell:Float, quantity:Int, code:String, category:Char): this(name, price_purchase, price_sell, quantity) {
        this.category = category
        this.code = "$category-$code"

        this.bought = quantity
    }

    /**
     * Method to sell a specific [quantity] of this [Product], modifying
     * [Product.quantity] and [Product.sold]
     */
    open fun sell_product(quantity: Int) {
        this.quantity -= quantity
        this.sold = quantity
    }

    /**
     * Method to return this product [Product.bought] and [Product.sold]
     *
     * @return Pair<Int, Int>: where the first value is bought and the second is sold
     */
    open fun balance() : Pair<Int, Int> {
        return Pair(this.bought, this.sold)
    }

    /**
     * Method that returns a [String] related to the [Product.category]
     * - 'C' == "COLECIONAVEL"
     * - 'R' == "ROUPA"
     * - 'E' == "ELECTRONIC"
     *
     * @return String? as above relations
     */
    open fun charToCategory():String? {
        when(this.category) {
            'C' -> {
                return "COLECIONAVEL"
            }
            'R' -> {
                return "ROUPA"
            }
            'E' -> {
                return "ELECTRONIC"
            }
        }
        return null
    }

}