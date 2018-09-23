package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class EmptySequenceMatcher implements SequenceMatcher {
    private static EmptySequenceMatcher SINGLETON = new EmptySequenceMatcher();

    public static EmptySequenceMatcher instance () {
        return SINGLETON;
    }

    private EmptySequenceMatcher() {}

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        return sequenceMatcherRequest.empty();
    }
}
