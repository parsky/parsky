package org.parsky.character;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoneOfCharacterMatcherTest {
    private NoneOfCharacterMatcher underTest = new NoneOfCharacterMatcher(new char[]{
        'A', '2', '!'
    });

    @Test
    public void match() throws Exception {
        assertTrue(underTest.matches('1'));
        assertTrue(underTest.matches('.'));
        assertTrue(underTest.matches('a'));
        assertTrue(underTest.matches('Z'));
    }

    @Test
    public void notMatch() throws Exception {
        assertFalse(underTest.matches('A'));
        assertFalse(underTest.matches('2'));
        assertFalse(underTest.matches('!'));
    }
}