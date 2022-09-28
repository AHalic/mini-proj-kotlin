enum class Material(val type:String) {
    paper("PAPEL"), plastic("PLASTICO"), steel("ACO"), mix("MISTURADO"), others("OUTROS")
}

enum class Rarity(val type:String) {
    common("COMUM"), uncommon("MEDIO"), rare("RARO"), ultra("RARISSIMO")
}

enum class TypeCollect(val type:String) {
    book("LIVRO"), toy("BONECO"), other("OUTROS")
}

enum class TypeElectro(val type:String) {
    video_game("VIDEO-GAMES"), game("JOGOS"), handheld("PORTATEIS"), other("OUTROS")
}

enum class Size(val type:String) {
    XS("PP"), S("P"), M("M"), L("G"), XL("GG"), XXL("XG"), XXXL("XXG")
}

enum class TypeClothes(val type:String) {
    shirt("CAMISA"), sweatshirt("MOLETON"), accessory("ACESSORIO")

}