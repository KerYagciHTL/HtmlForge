package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TextareaTest {
    @Test
    fun testToHtml() {
        assertEquals("<textarea name=\"n\" placeholder=\"p\" rows=\"3\" cols=\"10\"></textarea>\n", Textarea("n","p",3,10).toHtml())
    }
}
