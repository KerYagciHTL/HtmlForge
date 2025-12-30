package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CodeTest {
    @Test
    fun testToHtml() {
        assertEquals("<code>val x = 1</code>\n", Code("val x = 1").toHtml())
    }
}
