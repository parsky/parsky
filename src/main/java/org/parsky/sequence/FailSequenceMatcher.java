package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class FailSequenceMatcher implements SequenceMatcher {
    private final String message;

    public FailSequenceMatcher(String message) {
        this.message = message;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        return sequenceMatcherRequest.error(message);
    }
}
