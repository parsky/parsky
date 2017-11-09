package org.parsky.sequence.transform;

import org.junit.Test;
import org.parsky.sequence.model.Range;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ConstantTransformationTest {
    @Test
    public void transform() throws Exception {
        ConstantTransformation<Object> underTest = new ConstantTransformation<>("test");

        Object result = underTest.transform(new Object(), mock(Range.class), new Object());

        assertEquals(result, "test");
    }
}