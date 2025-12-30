package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HeadingTest {
    @Test
    fun testHeadingLevels() {
        assertEquals("<h1>Title</h1>\n", Heading(1, "Title").toHtml())
        assertEquals("<h2>Subtitle</h2>\n", Heading(2, "Subtitle").toHtml())
        assertEquals("<h6>Footer</h6>\n", Heading(6, "Footer").toHtml())
    }
}
