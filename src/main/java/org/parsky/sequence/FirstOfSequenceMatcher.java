package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.List;

public class FirstOfSequenceMatcher<C> implements SequenceMatcher<C> {
    private final List<SequenceMatcher<C>> sequenceMatchers;

    public FirstOfSequenceMatcher(List<SequenceMatcher<C>> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        for (SequenceMatcher<C> sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

            if (!result.isMismatch()) return result;
        }

        return SequenceMatcherResult.mismatch();
    }
}
