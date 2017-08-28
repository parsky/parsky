package org.parsky.character;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AnyCharacterMatcherTest {
    private AnyCharacterMatcher underTest = AnyCharacterMatcher.any();

    @Test
    public void match() throws Exception {
        assertTrue(underTest.matches('a'));
        assertTrue(underTest.matches('1'));
        assertTrue(underTest.matches('.'));
        assertTrue(underTest.matches('A'));
        assertTrue(underTest.matches('!'));
    }
}