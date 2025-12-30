package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BlockQuoteTest {
    @Test
    fun testToHtml() {
        val bq = BlockQuote("Quote")
        assertEquals("<blockquote>Quote</blockquote>\n", bq.toHtml())
    }
}
