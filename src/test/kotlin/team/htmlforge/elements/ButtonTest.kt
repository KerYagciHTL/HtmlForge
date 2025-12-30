package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ButtonTest {
    @Test
    fun testSimpleButton() {
        assertEquals("<button>OK</button>\n", Button("OK").toHtml())
    }

    @Test
    fun testButtonWithOnClick() {
        assertEquals("<button onclick=\"alert('hi')\">Click</button>\n", Button("Click", "alert('hi')").toHtml())
    }
}
