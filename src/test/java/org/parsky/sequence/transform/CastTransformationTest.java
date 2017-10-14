package org.parsky.sequence.transform;

import org.junit.Test;
import org.parsky.sequence.model.Range;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class CastTransformationTest {
    private final CastTransformation<Object, String> underTest = new CastTransformation<>(String.class);

    @Test
    public void cast() throws Exception {
        String result = underTest.transform(new Object(), mock(Range.class), "test");

        assertThat(result, is("test"));
    }
}