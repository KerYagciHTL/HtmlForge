package dev.kxrim.elements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HorizontalRuleTest {

    @Test
    void testToHtml() {
        HorizontalRule hr = new HorizontalRule();

        String expectedHtml = "<hr>\n";
        assertEquals(expectedHtml, hr.toHtml());
    }

    @Test
    void testImplementsElement() {
        HorizontalRule hr = new HorizontalRule();
        assertTrue(hr instanceof Element);
    }

    @Test
    void testMultipleInstances() {
        HorizontalRule hr1 = new HorizontalRule();
        HorizontalRule hr2 = new HorizontalRule();

        assertEquals(hr1.toHtml(), hr2.toHtml());
    }
}

