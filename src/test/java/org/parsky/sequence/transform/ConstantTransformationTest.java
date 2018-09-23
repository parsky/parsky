package org.parsky.sequence.transform;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.transform.context.TransformContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ConstantTransformationTest {
    @Test
    public void transform() throws Exception {
        ConstantTransformation underTest = new ConstantTransformation("test");

        Object result = underTest.transform(mock(SequenceMatcherRequest.class), new Object());

        assertEquals(result, "test");
    }
}