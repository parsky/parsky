package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class TestSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public TestSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest.testMode());

        if (result.matched()) {
            return sequenceMatcherRequest.empty();
        }

        return result;
    }
}
