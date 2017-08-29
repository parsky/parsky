package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ContentTransformationTest {
    private final Function<ContentTransformation.Request<String>, String> function = mock(Function.class);
    private ContentTransformation<String, String> underTest = new ContentTransformation<>(function);

    @Test
    public void transform() throws Exception {
        ContentNode<String> node = new ContentNode<>("test");

        MatchResult matchResult = mock(MatchResult.class);

        given(matchResult.getNode()).willReturn(node);
        given(function.apply((ContentTransformation.Request) argThat(new ReflectionEquals(new ContentTransformation.Request<>(matchResult, "test")))))
                .willReturn("wow");

        ContentNode<String> result = underTest.transform(matchResult);

        assertThat(result.getContent(), is("wow"));
    }
}