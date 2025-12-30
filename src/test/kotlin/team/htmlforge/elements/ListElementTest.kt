package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ListElementTest {
    @Test
    fun testUnorderedList() {
        val list = ListElement(false, "a", "b")
        assertTrue(list.toHtml().contains("<ul>"))
        assertTrue(list.toHtml().contains("<li>a</li>"))
    }

    @Test
    fun testOrderedList() {
        val list = ListElement(true, "1", "2")
        assertTrue(list.toHtml().contains("<ol>"))
        assertTrue(list.toHtml().contains("<li>1</li>"))
    }
}
