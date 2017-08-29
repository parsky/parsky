package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;
import org.parsky.sequence.model.tree.ListNode;
import org.parsky.sequence.model.tree.Node;
import org.parsky.sequence.model.tree.TextNode;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListContentTransformationTest {
    @Test
    public void contentNode() throws Exception {
        String content = "test";
        ContentNode<String> contentNode = new ContentNode<>(content);

        Function<ListContentTransformation.Request, String> function = mock(Function.class);
        MatchResult matchResult = mock(MatchResult.class);


        given(matchResult.getNode()).willReturn(contentNode);
        given(function.apply((ListContentTransformation.Request) argThat(new ReflectionEquals(new ListContentTransformation.Request(matchResult, Collections.singletonList((Object) content))))))
                .willReturn("result");

        ListContentTransformation underTest = new ListContentTransformation<>(function);

        ContentNode<String> result = underTest.transform(matchResult);

        assertThat(result.getContent(), is("result"));
    }

    @Test
    public void listNode() throws Exception {

        String content = "test";
        ContentNode<String> contentNode = new ContentNode<>(content);

        Function<ListContentTransformation.Request, String> function = mock(Function.class);
        MatchResult matchResult = mock(MatchResult.class);


        given(matchResult.getNode()).willReturn(new ListNode(Arrays.<Node>asList(contentNode)));
        given(function.apply((ListContentTransformation.Request) argThat(new ReflectionEquals(new ListContentTransformation.Request(matchResult, Collections.singletonList((Object) content))))))
                .willReturn("result");

        ListContentTransformation underTest = new ListContentTransformation<>(function);

        ContentNode<String> result = underTest.transform(matchResult);

        assertThat(result.getContent(), is("result"));

    }

    @Test
    public void textNode() throws Exception {
        Function<ListContentTransformation.Request, String> function = mock(Function.class);
        MatchResult matchResult = mock(MatchResult.class);


        given(matchResult.getNode()).willReturn(new TextNode("test".toCharArray(), 0, 4));
        given(function.apply((ListContentTransformation.Request) argThat(new ReflectionEquals(new ListContentTransformation.Request(matchResult, Collections.singletonList((Object) "test"))))))
                .willReturn("result");

        ListContentTransformation underTest = new ListContentTransformation<>(function);

        ContentNode<String> result = underTest.transform(matchResult);

        assertThat(result.getContent(), is("result"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void anotherNodeType() throws Exception {
        Function<ListContentTransformation.Request, String> function = mock(Function.class);
        MatchResult matchResult = mock(MatchResult.class);


        given(matchResult.getNode()).willReturn(mock(Node.class));
        given(function.apply((ListContentTransformation.Request) argThat(new ReflectionEquals(new ListContentTransformation.Request(matchResult, Collections.singletonList((Object) "test"))))))
                .willReturn("result");

        ListContentTransformation underTest = new ListContentTransformation<>(function);
        underTest.transform(matchResult);
    }
}