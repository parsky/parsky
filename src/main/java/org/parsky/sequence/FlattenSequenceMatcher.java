package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class FlattenSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public FlattenSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return SequenceMatcherResult.match(result.getJump(), sequenceMatcherRequest.text(result.getJump()));
        } else {
            return result;
        }
    }
}
