package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class CastTypedSequenceMatcher<T> implements TypedSequenceMatcher<T> {
    private final SequenceMatcher sequenceMatcher;

    public CastTypedSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        return sequenceMatcher.matches(sequenceMatcherRequest);
    }
}
