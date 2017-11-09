package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class MatchedTextSequenceMatcher<C> implements SequenceMatcher<C> {
    private final SequenceMatcher<C> sequenceMatcher;

    public MatchedTextSequenceMatcher(SequenceMatcher<C> sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return sequenceMatcherRequest.text(result.getJump());
        } else {
            return result;
        }
    }
}
