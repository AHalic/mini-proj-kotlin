enum class Material() {
    PAPEL, PLASTICO, ACO, MISTURADO, OUTROS
}

enum class Rarity() {
    COMUM, MEDIO, RARO, RARISSIMO
}

enum class TypeCollect() {
    LIVRO, BONECO, OUTROS
}

enum class TypeElectro(val type:String) {
    video_game("VIDEO-GAMES"), game("JOGOS"), handheld("PORTATEIS"), other("OUTROS");

    /**
     * Method to return the enum based on its type
     */
    @JvmName("valueOf1")
    fun valueOf(type:String) : TypeElectro {
        for (value in TypeElectro.values()) {
            if (type == value.type) {
                return value
            }
        }
        throw Exception("No electro type matches the given one")
    }
}

enum class Size() {
    PP, P, M, G, GG, XG, XXG
}

enum class TypeClothes() {
    CAMISA, MOLETON, ACESSORIO
}