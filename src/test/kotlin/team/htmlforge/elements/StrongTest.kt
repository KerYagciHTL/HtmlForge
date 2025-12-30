package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class StrongTest {
    @Test
    fun testToHtml() {
        assertEquals("<strong>bold</strong>", Strong("bold").toHtml())
    }
}
