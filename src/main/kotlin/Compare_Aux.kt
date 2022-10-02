import java.time.Year

/**
 * Compares [product] category to a passed [category]
 *
 * @return Boolean: true if equal or if [category] == "-", else false
 */
fun compareCategory(category: String?, product: Product): Boolean {
    return if (category != "-") product.charToCategory() == category!!.unaccentUpper() else true
}

/**
 * Compares [product] type to a passed [type]
 *
 * @return Boolean: true if equal or if [type] == "-", else false
 */
fun compareType(type: String?, product: Product): Boolean {
    return if (type == "-") true
    else try {
        product as Clothes
        product.type.toString() == type!!.unaccentUpper()
    }catch (e: java.lang.ClassCastException) {
        try {
            product as Collectable
            product.type.toString() == type!!.unaccentUpper()
        }catch (e2: java.lang.ClassCastException){
            try {
                product as Electronic
                product.type.toString() == type!!.unaccentUpper()
            } catch (e3: java.lang.ClassCastException) {
                false
            }
        }
    }
}

/**
 * Compares [product] size, if product is [Clothes], to a passed [size]
 *
 * @return Boolean: true if equal or if [size] == "-", else false
 */
fun compareSize(size: String?, product: Product): Boolean {
    return if (size == "-") true
            else try {
                product as Clothes
                product.size.toString() == size!!.unaccentUpper()
            }catch (e: java.lang.ClassCastException) {
                try {
                    product as Collectable
                    product.size.toString() == size!!.unaccentUpper()
                }catch (e2: java.lang.ClassCastException){
                    false
                }
            }
}

/**
 * Compares [product] color_primary and color_secundary, if product is [Clothes],
 * to passed [color_p] and [color_s]
 *
 * @return Boolean: true if both equal or if [color_p] == "-" && [color_s] == "-", else false
 */
fun compareColors(color_p: String?, color_s: String?, product: Product): Boolean {
    return if (color_p == "-" && color_s == "-") true
    else try {
        product as Clothes

        val bool_color_p:Boolean = if (color_p != "-") product.color_primary == color_p!!.unaccentUpper() else true
        val bool_color_s:Boolean = if (color_s != "-") product.color_secundary == color_s!!.unaccentUpper() else true

        bool_color_p && bool_color_s
    }catch (e: java.lang.ClassCastException) {
        false
    }
}

/**
 * Compares [product] version, if product is [Electronic], to a passed [version]
 *
 * @return Boolean: true if equal or if [version] == "-", else false
 */
fun compareVersion(version: String?, product: Product): Boolean {
    return if (version == "-") true
    else try {
        product as Electronic
        product.version == version!!.toInt()
    }catch (e: java.lang.ClassCastException) {
        false
    }
}

/**
 * Compares [product] year, if product is [Electronic], to a passed [year]
 *
 * @return Boolean: true if equal or if [year] == "-", else false
 */
fun compareYear(year: String?, product: Product): Boolean {
    return if (year == "-") true
    else try {
        product as Electronic
        product.year == Year.of(year!!.toInt())
    }catch (e: java.lang.ClassCastException) {
        false
    }
}

/**
 * Compares [product] material, if product is [Collectable], to a passed [material]
 *
 * @return Boolean: true if equal or if [material] == "-", else false
 */
fun compareMaterial(material: String?, product: Product): Boolean {
    return if (material == "-") true
    else try {
        product as Collectable
        product.material.toString() == material!!.unaccentUpper()
    }catch (e: java.lang.ClassCastException) {
        false
    }
}

/**
 * Compares [product] rarity, if product is [Collectable], to a passed [rarity]
 *
 * @return Boolean: true if equal or if [rarity] == "-", else false
 */
fun compareRarity(rarity: String?, product: Product): Boolean {
    return if (rarity == "-") true
    else try {
        product as Collectable
        product.rarity.toString() == rarity!!.unaccentUpper()
    }catch (e: java.lang.ClassCastException) {
        false
    }
}
