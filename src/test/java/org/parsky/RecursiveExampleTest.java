package org.parsky;

import org.junit.Test;
import org.parsky.sequence.ReferenceSequenceMatcher;
import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.model.SequenceMatcherResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.parsky.sequence.SequentTestUtils.request;

public class RecursiveExampleTest {
    @Test
    public void recursive() throws Exception {
        SequenceMatcher expressionMatcher = SequenceMatchers.string("a");

        ReferenceSequenceMatcher reference = SequenceMatchers.reference();

        SequenceMatcher sequenceMatcher = SequenceMatchers.firstOf(
                expressionMatcher,
                SequenceMatchers.sequence(
                        SequenceMatchers.string("("),
                        reference,
                        SequenceMatchers.string(")")
                )
        );

        SequenceMatcherResult result = reference.assign(sequenceMatcher).matches(request("(((a)))"));

        assertThat(result.matched(), is(true));
    }
}
