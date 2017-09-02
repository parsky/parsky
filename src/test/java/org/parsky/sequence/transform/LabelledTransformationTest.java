package org.parsky.sequence.transform;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class LabelledTransformationTest {
    private final String label = "label";
    private final Transformation transformation = mock(Transformation.class);
    private LabelledTransformation underTest = new LabelledTransformation(label, transformation);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void transform() throws Exception {
        MatchResult matchResult = mock(MatchResult.class);
        ContentNode contentNode = mock(ContentNode.class);

        given(transformation.transform(matchResult)).willReturn(contentNode);

        ContentNode result = underTest.transform(matchResult);

        assertSame(result, contentNode);
    }
    @Test
    public void transformException() throws Exception {
        MatchResult matchResult = mock(MatchResult.class);

        given(transformation.transform(matchResult)).willThrow(new RuntimeException("exception"));

        expectedException.expectMessage(label);

        underTest.transform(matchResult);
    }
}