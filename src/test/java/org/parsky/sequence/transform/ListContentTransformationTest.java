package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.hamcrest.FeatureMatcher;
import org.junit.Test;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;

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
        Function<ListContentTransformation.Request, Transformation.Result> function = mock(Function.class);
        ListContentTransformation underTest = new ListContentTransformation(function);
        ArrayList<Object> input = new ArrayList<>();

        given(function.apply(argThat(requestWithValues(input)))).willReturn(Transformation.Result.success(content));

        Object result = underTest.transform(mock(SequenceMatcherRequest.class), input);

        assertEquals(result, content);
    }

    private HamcrestArgumentMatcher<ListContentTransformation.Request> requestWithValues(final Object input) {
        return new HamcrestArgumentMatcher<>(new FeatureMatcher<ListContentTransformation.Request, Object>(is(input), "list", "list") {
            @Override
            protected Object featureValueOf(ListContentTransformation.Request request) {
                return request.getValues();
            }
        });
    }
}