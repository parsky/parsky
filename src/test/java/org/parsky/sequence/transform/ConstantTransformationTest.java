package org.parsky.sequence.transform;

import org.junit.Test;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ConstantTransformationTest {
    @Test
    public void transform() throws Exception {

        ConstantTransformation<String> underTest = new ConstantTransformation<>("test");
        ContentNode<String> result = underTest.transform(mock(MatchResult.class));

        assertEquals("test", result.getContent());
    }
}