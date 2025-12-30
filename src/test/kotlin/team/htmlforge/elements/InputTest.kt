package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class InputTest {
    @Test
    fun testToHtml() {
        assertEquals("<input type=\"text\" name=\"name\" placeholder=\"ph\"/>\n", Input("text","name","ph").toHtml())
    }
}
