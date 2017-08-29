package org.parsky.sequence;

import org.junit.Test;
import org.parsky.character.CharacterMatcher;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.TextNode;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.parsky.sequence.SequentTestUtils.request;

public class UntilSequenceMatcherTest {
    private UntilSequenceMatcher underTest = new UntilSequenceMatcher(new FirstOfSequenceMatcher(
            asList(new StringSequenceMatcher("test"))
    ));

    @Test
    public void matchWhenFindText() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("hello test"));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), instanceOf(TextNode.class));
        assertThat(((TextNode) result.getMatchResult().getNode()).getText(), is("hello "));
    }

    @Test
    public void matchWhenEndOfInput() throws Exception {
        SequenceMatcherResult result = new UntilSequenceMatcher(new FirstOfSequenceMatcher(
                asList(new CharacterSequenceMatcher(CharacterMatcher.endOfInput()))
        )).matches(request("hello test"));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getNode(), instanceOf(TextNode.class));
        assertThat(((TextNode) result.getMatchResult().getNode()).getText(), is("hello test"));
    }

    @Test
    public void noMatch() throws Exception {
        SequenceMatcherResult result = underTest.matches(request("hello"));

        assertThat(result.matched(), is(false));
    }
}