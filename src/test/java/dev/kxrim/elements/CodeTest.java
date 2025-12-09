package dev.kxrim.elements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    @Test
    void testConstructorAndToHtml() {
        Code code = new Code("System.out.println(\"Hello\");");
        
        String expectedHtml = "<code>System.out.println(\"Hello\");</code>\n";
        assertEquals(expectedHtml, code.toHtml());
    }

    @Test
    void testWithEmptyContent() {
        Code code = new Code("");
        
        String expectedHtml = "<code></code>\n";
        assertEquals(expectedHtml, code.toHtml());
    }

    @Test
    void testWithSpecialCharacters() {
        Code code = new Code("if (x < 5 && y > 10) { return true; }");
        
        String expectedHtml = "<code>if (x < 5 && y > 10) { return true; }</code>\n";
        assertEquals(expectedHtml, code.toHtml());
    }

    @Test
    void testWithHtmlTags() {
        Code code = new Code("<div>Hello</div>");
        
        String expectedHtml = "<code><div>Hello</div></code>\n";
        assertEquals(expectedHtml, code.toHtml());
    }

    @Test
    void testImplementsElement() {
        Code code = new Code("test");
        assertTrue(code instanceof Element);
    }
}

