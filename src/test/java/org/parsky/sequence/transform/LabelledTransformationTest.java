package org.parsky.sequence.transform;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.parsky.sequence.model.Range;

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
        Range range = mock(Range.class);

        given(transformation.transform(range, input)).willReturn(transformationResult);

        Object result = underTest.transform(range, input);

        assertSame(result, transformationResult);
    }
    @Test
    public void transformException() throws Exception {
        Object input = new Object();
        Range range = mock(Range.class);

        given(transformation.transform(range, input)).willThrow(new RuntimeException());

        expectedException.expectMessage(label);

        underTest.transform(range, input);
    }
}