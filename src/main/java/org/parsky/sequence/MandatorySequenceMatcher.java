package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class MandatorySequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher delegate;
    private final String errorMessage;

    public MandatorySequenceMatcher(SequenceMatcher delegate, String errorMessage) {
        this.delegate = delegate;
        this.errorMessage = errorMessage;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = delegate.matches(sequenceMatcherRequest);

        if (result.isError()) return result;

        if (!result.matched()) {
            return sequenceMatcherRequest.error(errorMessage);
        }

        return result;
    }
}
