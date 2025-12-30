package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LinkTest {
    @Test
    fun testToHtml() {
        assertEquals("<a href=\"https://example.com\">Example</a>\n", Link("https://example.com","Example").toHtml())
    }
}
