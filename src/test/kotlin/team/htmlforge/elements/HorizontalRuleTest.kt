package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HorizontalRuleTest {
    @Test
    fun testToHtml() {
        assertEquals("<hr/>\n", HorizontalRule().toHtml())
    }
}
