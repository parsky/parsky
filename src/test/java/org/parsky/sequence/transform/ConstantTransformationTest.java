package org.parsky.sequence.transform;

import org.junit.Test;
import org.parsky.sequence.model.Range;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ConstantTransformationTest {
    @Test
    public void transform() throws Exception {
        ConstantTransformation<Object, String> underTest = new ConstantTransformation<>("test");

        String result = underTest.transform(mock(Range.class), new Object());

        assertThat(result, is("test"));
    }
}