package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.transform.Transformation;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class TransformSequenceMatcherTest {
    private final SequenceMatcher sequenceMatcher = mock(SequenceMatcher.class);
    private final Transformation transformation = mock(Transformation.class);
    private TransformSequenceMatcher<Object, String, String> underTest = new TransformSequenceMatcher<>(sequenceMatcher, transformation);

    @Test
    public void matches() throws Exception {
        SequenceMatcherRequest request = SequentTestUtils.request("test");
        SequenceMatcherResult matchResult = request.text(1);

        given(sequenceMatcher.matches(request)).willReturn(request.text(1));

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.matched(), is(true));
    }

    @Test
    public void mismatch() throws Exception {
        SequenceMatcherRequest request = SequentTestUtils.request("test");

        given(sequenceMatcher.matches(request)).willReturn(SequenceMatcherResult.mismatch());

        SequenceMatcherResult result = underTest.matches(request);

        assertThat(result.isMismatch(), is(true));
        verifyZeroInteractions(transformation);
    }
}