package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class NotSequenceMatcher<C> implements SequenceMatcher<C> {
    private final SequenceMatcher<C> sequenceMatcher;

    public NotSequenceMatcher(SequenceMatcher<C> sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);
        
        switch (result.getType()) {
            case ERROR:
                return SequenceMatcherResult.error(sequenceMatcherRequest);
            case MATCHED:
                return SequenceMatcherResult.mismatch();
            case MISMATCH:
            default:
                return sequenceMatcherRequest.text(1);
        }
    }
}
