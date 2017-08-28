package org.parsky.character;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CharacterRangeMatcherTest {
    private CharacterRangeMatcher underTest = new CharacterRangeMatcher('a', 'z');

    @Test
    public void match() throws Exception {
        String allPossibilities = "abcdefghijklmnopqrstwxz";

        for (char character : allPossibilities.toCharArray()) {
            assertTrue(underTest.matches(character));
        }
    }

    @Test
    public void noMatch() throws Exception {
        assertFalse(underTest.matches('.'));
        assertFalse(underTest.matches('!'));
        assertFalse(underTest.matches(' '));
        assertFalse(underTest.matches('A'));
        assertFalse(underTest.matches('2'));
    }
}