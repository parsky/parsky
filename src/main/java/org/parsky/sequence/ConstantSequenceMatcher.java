package org.parsky.sequence;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class ConstantSequenceMatcher implements SequenceMatcher {
    private final Object value;

    public ConstantSequenceMatcher(Object value) {
        this.value = value;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        return SequenceMatcherResult.match(0, new MatchResult(sequenceMatcherRequest.range(0), value));
    }
}
