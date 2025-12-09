package dev.kxrim.elements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmphasisTest {

    @Test
    void testConstructorAndToHtml() {
        Emphasis emphasis = new Emphasis("Italic text");
        
        String expectedHtml = "<em>Italic text</em>\n";
        assertEquals(expectedHtml, emphasis.toHtml());
    }

    @Test
    void testWithEmptyContent() {
        Emphasis emphasis = new Emphasis("");
        
        String expectedHtml = "<em></em>\n";
        assertEquals(expectedHtml, emphasis.toHtml());
    }

    @Test
    void testWithSpecialCharacters() {
        Emphasis emphasis = new Emphasis("Text with <special> & \"characters\"");
        
        String expectedHtml = "<em>Text with <special> & \"characters\"</em>\n";
        assertEquals(expectedHtml, emphasis.toHtml());
    }

    @Test
    void testImplementsElement() {
        Emphasis emphasis = new Emphasis("Test");
        assertTrue(emphasis instanceof Element);
    }
}

