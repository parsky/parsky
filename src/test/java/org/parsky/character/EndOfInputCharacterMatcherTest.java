package org.parsky.character;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EndOfInputCharacterMatcherTest {
    private EndOfInputCharacterMatcher underTest = EndOfInputCharacterMatcher.endOfInput();

    @Test
    public void endOfInput() throws Exception {
        assertTrue(underTest.matches(EndOfInputCharacterMatcher.EOI));
    }

    @Test
    public void notEndOfInput() throws Exception {
        assertFalse(underTest.matches('a'));
        assertFalse(underTest.matches('.'));
        assertFalse(underTest.matches(' '));
        assertFalse(underTest.matches('1'));
    }
}