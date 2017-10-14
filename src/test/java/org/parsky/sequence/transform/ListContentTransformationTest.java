package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.hamcrest.FeatureMatcher;
import org.junit.Test;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.parsky.sequence.model.Range;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListContentTransformationTest {
    @Test
    public void transform() throws Exception {
        String content = "test";
        Function<ListContentTransformation.Request<Object, Object>, String> function = mock(Function.class);
        ListContentTransformation<Object, Object, String> underTest = new ListContentTransformation<>(function);
        ArrayList<Object> input = new ArrayList<>();

        given(function.apply(argThat(requestWithValues(input)))).willReturn(content);

        Object result = underTest.transform(new Object(), mock(Range.class), input);

        assertEquals(result, content);
    }

    private HamcrestArgumentMatcher<ListContentTransformation.Request<Object, Object>> requestWithValues(final Object input) {
        return new HamcrestArgumentMatcher<>(new FeatureMatcher<ListContentTransformation.Request<Object, Object>, Object>(is(input), "list", "list") {
            @Override
            protected Object featureValueOf(ListContentTransformation.Request<Object, Object> request) {
                return request.getValues();
            }
        });
    }
}