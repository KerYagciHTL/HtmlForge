package team.htmlforge.elements

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ParagraphTest {

    @Test
    fun testConstructorAndToHtml() {
        val paragraph = Paragraph("This is a paragraph.")

        val expectedHtml = "<p>This is a paragraph.</p>\n"
        assertEquals(expectedHtml, paragraph.toHtml())
    }

    @Test
    fun testWithEmptyContent() {
        val paragraph = Paragraph("")

        val expectedHtml = "<p></p>\n"
        assertEquals(expectedHtml, paragraph.toHtml())
    }

    @Test
    fun testWithLongContent() {
        val longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        val paragraph = Paragraph(longText)

        val expectedHtml = "<p>$longText</p>\n"
        assertEquals(expectedHtml, paragraph.toHtml())
    }

    @Test
    fun testWithSpecialCharacters() {
        val paragraph = Paragraph("Text with <special> & \"characters\"")

        val expectedHtml = "<p>Text with <special> & \"characters\"</p>\n"
        assertEquals(expectedHtml, paragraph.toHtml())
    }

    @Test
    fun testWithLineBreaks() {
        val paragraph = Paragraph("Line 1\nLine 2\nLine 3")

        val expectedHtml = "<p>Line 1\nLine 2\nLine 3</p>\n"
        assertEquals(expectedHtml, paragraph.toHtml())
    }

    @Test
    fun testImplementsElement() {
        val paragraph = Paragraph("Test")
        assertTrue(paragraph is Element)
    }
}
