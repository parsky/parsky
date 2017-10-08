package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class TestSequenceMatcher<C> implements SequenceMatcher<C, Void> {
    private final SequenceMatcher<C, ?> sequenceMatcher;

    public TestSequenceMatcher(SequenceMatcher<C, ?> sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult<Void> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult<?> result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return sequenceMatcherRequest.empty();
        }

        return result.cast();
    }
}
