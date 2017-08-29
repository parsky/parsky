package org.parsky.sequence.model;

import com.google.common.base.Optional;
import org.junit.Test;
import org.parsky.sequence.model.tree.Node;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SequenceMatcherResultTest {
    @Test
    public void withJump() throws Exception {
        MatchResult matchResult = mock(MatchResult.class);

        SequenceMatcherResultType type = SequenceMatcherResultType.ERROR;
        Optional<MatchResult> matchResultOptional = Optional.of(matchResult);

        SequenceMatcherResult underTest = new SequenceMatcherResult(type, 0, matchResultOptional);

        SequenceMatcherResult result = underTest.withJump(1);

        assertThat(result.getType(), is(type));
        assertThat(result.getJump(), is(1));
        assertThat(result.getMatchResult(), is(matchResult));
    }

    @Test
    public void withNode() throws Exception {
        Node node = mock(Node.class);
        MatchResult matchResult = mock(MatchResult.class);
        MatchResult anotherMatchResult = mock(MatchResult.class);

        SequenceMatcherResultType type = SequenceMatcherResultType.ERROR;
        Optional<MatchResult> matchResultOptional = Optional.of(matchResult);

        SequenceMatcherResult underTest = new SequenceMatcherResult(type, 0, matchResultOptional);

        given(matchResult.with(node)).willReturn(anotherMatchResult);

        SequenceMatcherResult result = underTest.withNode(node);

        assertThat(result.getType(), is(type));
        assertThat(result.getJump(), is(0));
        assertThat(result.getMatchResult(), is(anotherMatchResult));
    }

    @Test
    public void isError() throws Exception {
        assertTrue(SequenceMatcherResult.error(mock(SequenceMatcherRequest.class)).isError());
        assertFalse(new SequenceMatcherResult(SequenceMatcherResultType.MATCHED, 0, Optional.<MatchResult>absent()).isError());
        assertFalse(new SequenceMatcherResult(SequenceMatcherResultType.MISMATCH, 0, Optional.<MatchResult>absent()).isError());
    }

    @Test
    public void isMatch() throws Exception {
        assertTrue(SequenceMatcherResult.match(0, mock(MatchResult.class)).matched());
        assertFalse(new SequenceMatcherResult(SequenceMatcherResultType.ERROR, 0, Optional.<MatchResult>absent()).matched());
        assertFalse(new SequenceMatcherResult(SequenceMatcherResultType.MISMATCH, 0, Optional.<MatchResult>absent()).matched());
    }

    @Test
    public void isMismatch() throws Exception {
        assertTrue(SequenceMatcherResult.mismatch().isMismatch());
        assertFalse(new SequenceMatcherResult(SequenceMatcherResultType.ERROR, 0, Optional.<MatchResult>absent()).isMismatch());
        assertFalse(new SequenceMatcherResult(SequenceMatcherResultType.MATCHED, 0, Optional.<MatchResult>absent()).isMismatch());
    }
}