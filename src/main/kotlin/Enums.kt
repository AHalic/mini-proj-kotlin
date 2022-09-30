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
    video_game("VIDEO-GAMES"), game("JOGOS"), handheld("PORTATEIS"), other("OUTROS")
}

enum class Size() {
    PP, P, M, G, GG, XG, XXG
}

enum class TypeClothes() {
    CAMISA, MOLETON, ACESSORIO

}