package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class MandatorySequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher delegate;

    public MandatorySequenceMatcher(SequenceMatcher delegate) {
        this.delegate = delegate;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = delegate.matches(sequenceMatcherRequest);

        if (!result.matched()) {
            return SequenceMatcherResult.error(sequenceMatcherRequest);
        }
        return result;
    }
}
