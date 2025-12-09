package dev.kxrim.elements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeadingTest {

    @Test
    void testHeadingLevel1() {
        Heading heading = new Heading(1, "Title");

        String expectedHtml = "<h1>Title</h1>\n";
        assertEquals(expectedHtml, heading.toHtml());
    }

    @Test
    void testHeadingLevel2() {
        Heading heading = new Heading(2, "Subtitle");

        String expectedHtml = "<h2>Subtitle</h2>\n";
        assertEquals(expectedHtml, heading.toHtml());
    }

    @Test
    void testHeadingLevel3() {
        Heading heading = new Heading(3, "Section");

        String expectedHtml = "<h3>Section</h3>\n";
        assertEquals(expectedHtml, heading.toHtml());
    }

    @Test
    void testHeadingLevel4() {
        Heading heading = new Heading(4, "Subsection");

        String expectedHtml = "<h4>Subsection</h4>\n";
        assertEquals(expectedHtml, heading.toHtml());
    }

    @Test
    void testHeadingLevel5() {
        Heading heading = new Heading(5, "Minor heading");

        String expectedHtml = "<h5>Minor heading</h5>\n";
        assertEquals(expectedHtml, heading.toHtml());
    }

    @Test
    void testHeadingLevel6() {
        Heading heading = new Heading(6, "Smallest heading");

        String expectedHtml = "<h6>Smallest heading</h6>\n";
        assertEquals(expectedHtml, heading.toHtml());
    }

    @Test
    void testInvalidLevelTooLow() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Heading(0, "Invalid");
        });

        assertEquals("Heading level must be between 1 and 6", exception.getMessage());
    }

    @Test
    void testInvalidLevelTooHigh() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Heading(7, "Invalid");
        });

        assertEquals("Heading level must be between 1 and 6", exception.getMessage());
    }

    @Test
    void testInvalidLevelNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Heading(-1, "Invalid");
        });

        assertEquals("Heading level must be between 1 and 6", exception.getMessage());
    }

    @Test
    void testWithEmptyContent() {
        Heading heading = new Heading(1, "");

        String expectedHtml = "<h1></h1>\n";
        assertEquals(expectedHtml, heading.toHtml());
    }

    @Test
    void testWithSpecialCharacters() {
        Heading heading = new Heading(2, "Title with <special> & \"characters\"");

        String expectedHtml = "<h2>Title with <special> & \"characters\"</h2>\n";
        assertEquals(expectedHtml, heading.toHtml());
    }

    @Test
    void testImplementsElement() {
        Heading heading = new Heading(1, "Test");
        assertTrue(heading instanceof Element);
    }
}

