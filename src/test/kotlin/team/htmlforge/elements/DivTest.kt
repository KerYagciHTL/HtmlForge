package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DivTest {
    @Test
    fun testDivSimpleContent() {
        val div = Div("content")
        assertEquals("<div>content</div>\n", div.toHtml())
    }

    @Test
    fun testDivWithClassAndId() {
        val div = Div("content", "class-name", "my-id")
        assertEquals("<div class=\"class-name\" id=\"my-id\">content</div>\n", div.toHtml())
    }

    @Test
    fun testDivNesting() {
        val parent = Div()
        val child = Div("child")
        parent.div(child)
        assertTrue(parent.toHtml().contains("<div>child</div>"))
    }

    @Test
    fun testFluentApi() {
        val div = Div("container", true)
            .heading(3, "Title")
            .paragraph("Para")
            .button("Go")
        assertTrue(div.toHtml().contains("<h3>Title</h3>"))
        assertTrue(div.toHtml().contains("<button>Go</button>"))
    }
}
