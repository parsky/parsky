package org.parsky.sequence;

import org.junit.Test;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.junit.Assert.assertEquals;

public class ConstantSequenceMatcherTest {
    @Test
    public void constant() throws Exception {
        SequenceMatcherResult result = new ConstantSequenceMatcher(3)
                .matches(SequentTestUtils.request("asd"));


        assertEquals(3, result.getMatchResult().getValue());
    }
}