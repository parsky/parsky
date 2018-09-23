package org.parsky.sequence.transform;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.parsky.sequence.model.SequenceMatcherRequest;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class LabelledTransformationTest {
    private final String label = "label";
    private final Transformation transformation = mock(Transformation.class);
    private LabelledTransformation underTest = new LabelledTransformation(label, transformation);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void transform() throws Exception {
        Object input = new Object();
        Object transformationResult = new Object();
        SequenceMatcherRequest transformContext = mock(SequenceMatcherRequest.class);

        given(transformation.transform(transformContext, input)).willReturn(Transformation.Result.success(transformationResult));

        Object result = underTest.transform(transformContext, input).getResult();

        assertSame(result, transformationResult);
    }
    @Test
    public void transformException() throws Exception {
        Object input = new Object();
        SequenceMatcherRequest transformContext = mock(SequenceMatcherRequest.class);

        given(transformation.transform(transformContext, input)).willThrow(new RuntimeException());

        expectedException.expectMessage(label);

        underTest.transform(transformContext, input);
    }
}