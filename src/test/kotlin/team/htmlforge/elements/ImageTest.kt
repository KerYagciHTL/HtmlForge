package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ImageTest {
    @Test
    fun testToHtml() {
        assertEquals("<img src=\"a.png\" alt=\"A\"/>\n", Image("a.png", "A").toHtml())
    }
}
