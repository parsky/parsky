package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class EndOfInputSequenceMatcher implements SequenceMatcher {
    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        if (sequenceMatcherRequest.isEndOfInput()) {
            return sequenceMatcherRequest.empty();
        } else {
            return SequenceMatcherResult.mismatch();
        }
    }
}
