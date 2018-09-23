package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.transform.context.TransformContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ContentTransformationTest {
    private final Function<ContentTransformation.Request<String>, Transformation.Result> function = mock(Function.class);
    private ContentTransformation<String> underTest = new ContentTransformation<>(function);

    @Test
    public void transform() throws Exception {
        SequenceMatcherRequest transformContext = mock(SequenceMatcherRequest.class);
        given(function.apply(argThat(requestWithContent(equalTo("test"))))).willReturn(Transformation.Result.success("result"));

        String result = (String) underTest.transform(transformContext, "test").getResult();

        assertThat(result, is("result"));
    }

    private ArgumentMatcher<ContentTransformation.Request> requestWithContent(Matcher matcher) {
        return new HamcrestArgumentMatcher<>(new FeatureMatcher<ContentTransformation.Request, Object>(matcher, "value", "value") {
            @Override
            protected Object featureValueOf(ContentTransformation.Request request) {
                return request.getValue();
            }
        });
    }


}