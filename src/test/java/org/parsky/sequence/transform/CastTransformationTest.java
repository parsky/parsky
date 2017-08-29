package org.parsky.sequence.transform;

import org.junit.Test;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CastTransformationTest {
    @Test
    public void cast() throws Exception {
        MatchResult matchResult = mock(MatchResult.class);

        given(matchResult.getNode()).willReturn(new ContentNode<>("test"));

        ContentNode<String> result = new CastTransformation<>(String.class)
                .transform(matchResult);

        assertEquals("test", result.getContent());
    }
}