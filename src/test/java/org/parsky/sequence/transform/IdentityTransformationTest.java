package org.parsky.sequence.transform;

import org.junit.Test;
import org.parsky.sequence.model.Range;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class IdentityTransformationTest {
    @Test
    public void transform() throws Exception {
        Object input = new Object();
        Object result = new IdentityTransformation<>()
                .transform(new Object(), mock(Range.class), input);

        assertSame(input, result);
    }
}