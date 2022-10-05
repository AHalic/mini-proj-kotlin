/**
 * Enum Class representing the existing [Collectable] materials
 * - PAPEL
 * - PLASTICO
 * - MISTURADO
 * - OUTROS
 */
enum class Material() {
    PAPEL, PLASTICO, ACO, MISTURADO, OUTROS
}

/**
 * Enum Class representing the existing [Collectable] rarities
 * - COMUM
 * - MEDIO
 * - RARO
 * - RARISSIMO
 */
enum class Rarity() {
    COMUM, MEDIO, RARO, RARISSIMO
}

/**
 * Enum Class representing the existing [Collectable] types
 * - LIVRO
 * - BONECO
 * - OUTROS
 */
enum class TypeCollect() {
    LIVRO, BONECO, OUTROS
}

/**
 * Enum Class representing the existing [Electronic] types
 * - VIDEO-GAME
 * - JOGO
 * - PORTATIL
 * - OUTROS
 */
enum class TypeElectro(val type:String) {
    video_game("VIDEO-GAME"), game("JOGO"), handheld("PORTATEIL"), other("OUTROS");

    companion object {
        /**
         * Method to return the enum based on its [type] string value
         */
        fun valueByType(type:String) : TypeElectro {
            for (value in TypeElectro.values()) {
                if (type == value.type) {
                    return value
                }
            }
            throw Exception("No electro type matches the given one")
        }
    }
    override fun toString(): String {
        return this.type
    }
}

/**
 * Enum Class representing the existing [Clothes] sizes
 * - PP
 * - P
 * - M
 * - G
 * - GG
 * - XG
 * - XXG
 */
enum class Size() {
    PP, P, M, G, GG, XG, XXG
}

/**
 * Enum Class representing the existing [Clothes] types
 * - CAMISA
 * - MOLETON
 * - ACESSORIO
 */
enum class TypeClothes() {
    CAMISA, MOLETON, ACESSORIO
}