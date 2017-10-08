package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.List;

public class FirstOfSequenceMatcher<C, R> implements SequenceMatcher<C, R> {
    private final List<SequenceMatcher<C, R>> sequenceMatchers;

    public FirstOfSequenceMatcher(List<SequenceMatcher<C, R>> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public SequenceMatcherResult<R> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        for (SequenceMatcher<C, R> sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult<R> result = sequenceMatcher.matches(sequenceMatcherRequest);

            if (!result.isMismatch()) return result;
        }

        return SequenceMatcherResult.mismatch();
    }
}
