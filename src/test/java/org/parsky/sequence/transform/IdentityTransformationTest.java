package org.parsky.sequence.transform;

import org.junit.Test;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class IdentityTransformationTest {
    @Test
    public void transform() throws Exception {
        MatchResult matchResult = mock(MatchResult.class);
        ContentNode contentNode = mock(ContentNode.class);

        given(matchResult.getNode()).willReturn(contentNode);

        ContentNode<Object> result = new IdentityTransformation<>()
                .transform(matchResult);

        assertSame(result, contentNode);
    }
}