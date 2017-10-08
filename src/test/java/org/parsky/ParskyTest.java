package org.parsky;

import org.junit.Test;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class ParskyTest {
    @Test
    public void parseMismatch() throws Exception {
        TransformSequenceMatcher<Object, String, String> matcher = mock(TransformSequenceMatcher.class);
        Parsky<Object, String> underTest = new Parsky<>(matcher);

        given(matcher.matches(any(SequenceMatcherRequest.class))).willReturn(SequenceMatcherResult.mismatch());

        Parsky.Result<String> result = underTest.parse(new Object(),"content");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.isError(), is(false));
    }

    @Test
    public void parseMatch() throws Exception {
        int offset = 1;
        String output = "output";

        MatchResult matchResult = mock(MatchResult.class);
        TransformSequenceMatcher<Object, String, String> matcher = mock(TransformSequenceMatcher.class);
        Parsky<Object, String> underTest = new Parsky<>(matcher);

        given(matcher.matches(any(SequenceMatcherRequest.class))).willReturn(SequenceMatcherResult.match(offset, matchResult));
        given(matchResult.getValue()).willReturn(output);

        Parsky.Result<String> result = underTest.parse(new Object(),"content");

        assertThat(result.isSuccess(), is(true));
        assertThat(result.isError(), is(false));
        assertThat(result.output(), is(output));
        assertThat(result.getOffset(), is(offset));
    }

    @Test
    public void parseError() throws Exception {
        SequenceMatcherRequest sequenceMatcherRequest = mock(SequenceMatcherRequest.class);
        TransformSequenceMatcher<Object, String, String> matcher = mock(TransformSequenceMatcher.class);
        Parsky<Object, String> underTest = new Parsky<>(matcher);

        SequenceMatcherResult sequenceMatcherResult = SequenceMatcherResult.error(sequenceMatcherRequest);
        given(matcher.matches(any(SequenceMatcherRequest.class))).willReturn(sequenceMatcherResult);

        Parsky.Result<String> result = underTest.parse(new Object(),"content");

        assertThat(result.isSuccess(), is(false));
        assertThat(result.isError(), is(true));
    }
}