package dev.kxrim.elements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListElementTest {

    @Test
    void testUnorderedList() {
        ListElement list = new ListElement(false, "Item 1", "Item 2", "Item 3");

        String expectedHtml = "<ul>\n<li>Item 1</li>\n<li>Item 2</li>\n<li>Item 3</li>\n</ul>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testOrderedList() {
        ListElement list = new ListElement(true, "First", "Second", "Third");

        String expectedHtml = "<ol>\n<li>First</li>\n<li>Second</li>\n<li>Third</li>\n</ol>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testUnorderedListSingleItem() {
        ListElement list = new ListElement(false, "Only one");

        String expectedHtml = "<ul>\n<li>Only one</li>\n</ul>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testOrderedListSingleItem() {
        ListElement list = new ListElement(true, "Only one");

        String expectedHtml = "<ol>\n<li>Only one</li>\n</ol>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testEmptyUnorderedList() {
        ListElement list = new ListElement(false);

        String expectedHtml = "<ul>\n</ul>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testEmptyOrderedList() {
        ListElement list = new ListElement(true);

        String expectedHtml = "<ol>\n</ol>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testWithEmptyStringItem() {
        ListElement list = new ListElement(false, "Item 1", "", "Item 3");

        String expectedHtml = "<ul>\n<li>Item 1</li>\n<li></li>\n<li>Item 3</li>\n</ul>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testWithSpecialCharacters() {
        ListElement list = new ListElement(false, "Item with <tags>", "Item with \"quotes\"", "Item with & ampersand");

        String expectedHtml = "<ul>\n<li>Item with <tags></li>\n<li>Item with \"quotes\"</li>\n<li>Item with & ampersand</li>\n</ul>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testManyItems() {
        ListElement list = new ListElement(true, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        String expectedHtml = "<ol>\n<li>1</li>\n<li>2</li>\n<li>3</li>\n<li>4</li>\n<li>5</li>\n<li>6</li>\n<li>7</li>\n<li>8</li>\n<li>9</li>\n<li>10</li>\n</ol>\n";
        assertEquals(expectedHtml, list.toHtml());
    }

    @Test
    void testImplementsElement() {
        ListElement list = new ListElement(false, "Test");
        assertTrue(list instanceof Element);
    }
}

