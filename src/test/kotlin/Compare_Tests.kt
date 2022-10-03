import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Year

class Compare_Tests {
    // Tests variables
    private val collectable:Collectable = Collectable(TypeCollect.LIVRO, Material.PAPEL, null, Rarity.COMUM, "TESTE", 14f, 27f, 3, "TST1")
    private val electro:Electronic = Electronic(TypeElectro.video_game, 2, Year.of(2020), "TESTE", 14f, 27f, 3, "TST1")
    private val clothes:Clothes = Clothes(TypeClothes.CAMISA, Size.M, "PINK", "WHITE","TESTE", 14f, 27f, 3, "TST1")


    @Test
    @DisplayName("Tests the comparison between a product's category and a string") // opcional
    fun testCompareCategory(){
        Assertions.assertTrue(compareCategory("COLECIONAVEL", collectable as Product))
        Assertions.assertTrue(compareCategory("ELETRONICO", electro as Product))
        Assertions.assertTrue(compareCategory("ROUPA", clothes as Product))
        Assertions.assertTrue(compareCategory("-", collectable as Product))

        Assertions.assertFalse(compareCategory("ROUPA", collectable as Product))
    }


    @Test
    @DisplayName("Tests the comparison between a product's type and a string") // opcional
    fun testCompareType(){
        Assertions.assertTrue(compareType("LIVRO", collectable as Product))
        Assertions.assertTrue(compareType("VIDEO-GAME", electro as Product))
        Assertions.assertTrue(compareType("-", collectable as Product))

        Assertions.assertFalse(compareType("JOGO", collectable as Product))
        Assertions.assertFalse(compareType("BONECO", collectable as Product))
    }

    @Test
    @DisplayName("Tests the comparison between a product's size and a string") // opcional
    fun testCompareSize(){
        Assertions.assertTrue(compareSize("M", clothes as Product))
        Assertions.assertTrue(compareSize("-", collectable as Product))

        Assertions.assertFalse(compareSize("GG", clothes as Product))
        Assertions.assertFalse(compareSize("M", electro as Product))
    }

    @Test
    @DisplayName("Tests the comparison between a product's colors and a string") // opcional
    fun testCompareColors(){
        Assertions.assertTrue(compareColors("PINK", "WHITE", clothes as Product))
        Assertions.assertTrue(compareColors("-", "WHITE", clothes as Product))
        Assertions.assertTrue(compareColors("PINK", "-", clothes as Product))
        Assertions.assertTrue(compareColors("-", "-", collectable as Product))

        Assertions.assertFalse(compareColors("BLACK", "WHITE", clothes as Product))
        Assertions.assertFalse(compareColors("PINK", "BLUE", clothes as Product))
        Assertions.assertFalse(compareColors("PINK", "WHITE", electro as Product))
        Assertions.assertFalse(compareColors("PINK", "-", electro as Product))
    }

    @Test
    @DisplayName("Tests the comparison between a product's version and a string") // opcional
    fun testCompareVersion(){
        Assertions.assertTrue(compareVersion("2", electro as Product))
        Assertions.assertTrue(compareVersion("-", collectable as Product))

        Assertions.assertFalse(compareVersion("2", clothes as Product))
        Assertions.assertFalse(compareVersion("4", electro as Product))
    }

    @Test
    @DisplayName("Tests the comparison between a product's year and a string") // opcional
    fun testCompareYear(){
        Assertions.assertTrue(compareYear("2020", electro as Product))
        Assertions.assertTrue(compareYear("-", collectable as Product))

        Assertions.assertFalse(compareYear("2020", clothes as Product))
        Assertions.assertFalse(compareYear("2019", electro as Product))
    }

    @Test
    @DisplayName("Tests the comparison between a product's material and a string") // opcional
    fun testCompareMaterial(){
        Assertions.assertTrue(compareMaterial("PAPEL", collectable as Product))
        Assertions.assertTrue(compareMaterial("-", electro as Product))

        Assertions.assertFalse(compareMaterial("PAPEL", clothes as Product))
        Assertions.assertFalse(compareMaterial("PLASTICO", collectable as Product))
    }

    @Test
    @DisplayName("Tests the comparison between a product's rarity and a string") // opcional
    fun testCompareRarity(){
        Assertions.assertTrue(compareRarity("COMUM", collectable as Product))
        Assertions.assertTrue(compareRarity("-", electro as Product))

        Assertions.assertFalse(compareRarity("COMUM", clothes as Product))
        Assertions.assertFalse(compareRarity("RARO", collectable as Product))
    }
}