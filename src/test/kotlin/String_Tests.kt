import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class String_Tests {
    @Test
    @DisplayName("Tests the unaccent and upper case string function") // opcional
    fun testUnaccentUpper(){
        Assertions.assertEquals("FABRICACAO", "fabricação".unaccentUpper())

        Assertions.assertNotEquals("fabricacao", "fabricação".unaccentUpper())
        Assertions.assertNotEquals("FABRICAÇÃO", "fabricação".unaccentUpper())
    }

    @Test
    @DisplayName("Tests the check hyphen string function") // opcional
    fun testcheckHyphen(){
        Assertions.assertEquals("cachorro", "cachorro".checkHyphen())
        Assertions.assertEquals(null, "-".checkHyphen())
        Assertions.assertEquals("video-game", "video-game".checkHyphen())
    }

    @Test
    @DisplayName("Tests the format float decimal function") // opcional
    fun testFormatDecimal(){
        Assertions.assertEquals("34", formatDecimal(34.0f))
        Assertions.assertEquals("56.2", formatDecimal(56.20f))
        Assertions.assertEquals("1904", formatDecimal(1904f))
        Assertions.assertEquals("34.09", formatDecimal(34.0946f))
    }
}