package org.parsky.sequence;

import org.junit.Test;
import org.parsky.character.CharacterMatchers;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class UntilSequenceMatcherTest {
    private UntilSequenceMatcher underTest = new UntilSequenceMatcher(new FirstOfSequenceMatcher(
            Arrays.<SequenceMatcher>asList(new StringSequenceMatcher("test"))
    ));

    @Test
    public void matchWhenFindText() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("hello test"));

        assertThat(result.matched(), is(true));
    }

    @Test
    public void matchWhenEndOfInput() throws Exception {
        SequenceMatcherResult result = new UntilSequenceMatcher(new FirstOfSequenceMatcher(
                Arrays.<SequenceMatcher>asList(new CharacterSequenceMatcher(CharacterMatchers.endOfInput()))
        )).matches(request("hello test"));

        assertThat(result.matched(), is(true));
    }

    @Test
    public void noMatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("hello"));

        assertThat(result.matched(), is(false));
    }
}