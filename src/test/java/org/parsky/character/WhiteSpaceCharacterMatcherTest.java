package org.parsky.character;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WhiteSpaceCharacterMatcherTest {
    private WhiteSpaceCharacterMatcher underTest = WhiteSpaceCharacterMatcher.whitespace();

    @Test
    public void match() throws Exception {
        assertTrue(underTest.matches(' '));
        assertTrue(underTest.matches('\n'));
        assertTrue(underTest.matches('\t'));
        assertTrue(underTest.matches('\r'));
    }

    @Test
    public void noMatch() throws Exception {
        assertFalse(underTest.matches('1'));
        assertFalse(underTest.matches('A'));
        assertFalse(underTest.matches('!'));
        assertFalse(underTest.matches('_'));
    }
}