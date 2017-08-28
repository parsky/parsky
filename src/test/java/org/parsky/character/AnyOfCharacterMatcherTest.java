package org.parsky.character;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnyOfCharacterMatcherTest {
    private AnyOfCharacterMatcher underTest = new AnyOfCharacterMatcher(new char[]{
        '.', '!', 'a'
    });

    @Test
    public void match() throws Exception {
        assertTrue(underTest.matches('!'));
        assertTrue(underTest.matches('a'));
        assertTrue(underTest.matches('.'));
    }

    @Test
    public void notMatch() throws Exception {
        assertFalse(underTest.matches('A'));
        assertFalse(underTest.matches('"'));
        assertFalse(underTest.matches('?'));
    }
}