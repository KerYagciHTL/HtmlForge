package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EmphasisTest {
    @Test
    fun testToHtml() {
        assertEquals("<em>italic</em>", Emphasis("italic").toHtml())
    }
}
