package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class OptionalSequenceMatcher<C> implements SequenceMatcher<C> {
    private final SequenceMatcher<C> sequenceMatcher;

    public OptionalSequenceMatcher(SequenceMatcher<C> sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.isMismatch()) return sequenceMatcherRequest.empty();

        return result;
    }
}
