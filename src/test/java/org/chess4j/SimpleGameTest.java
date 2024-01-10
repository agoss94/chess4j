package org.chess4j;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleGameTest {

    @Test
    void testUciFormatNormalInput() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("e2g8");
        assertTrue(m.matches());
        assertEquals("e2", m.group(1));
        assertEquals("g8", m.group(2));
        assertNull(null, m.group(3));
    }

    @Test
    void testUciFormatFirstCharNotMatching() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("i2g8");
        assertFalse(m.matches());
    }

    @Test
    void testUciFormatSecondNumberNotMatching() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("e9g8");
        assertFalse(m.matches());
    }

    @Test
    void testUciFormatThirdCharNotMatching() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("e4p8");
        assertFalse(m.matches());
    }

    @Test
    void testUciFormatFourthCharNotMatching() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("e4g9");
        assertFalse(m.matches());
    }

    @Test
    void testUciWithPromotionToQueen() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("e7e8q");
        assertTrue(m.matches());
        assertEquals("e7", m.group(1));
        assertEquals("e8", m.group(2));
        assertEquals("q", m.group(3));
    }

    @Test
    void testUciWithPromotionToKnight() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("e7e8k");
        assertTrue(m.matches());
        assertEquals("e7", m.group(1));
        assertEquals("e8", m.group(2));
        assertEquals("k", m.group(3));
    }

    @Test
    void testUciWithPromotionToBishop() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("e7e8b");
        assertTrue(m.matches());
        assertEquals("e7", m.group(1));
        assertEquals("e8", m.group(2));
        assertEquals("b", m.group(3));
    }

    @Test
    void testUciWithPromotionToRook() {
        Matcher m = ChessGame.UCI_FORMAT.matcher("e7e8r");
        assertTrue(m.matches());
        assertEquals("e7", m.group(1));
        assertEquals("e8", m.group(2));
        assertEquals("r", m.group(3));
    }
}
