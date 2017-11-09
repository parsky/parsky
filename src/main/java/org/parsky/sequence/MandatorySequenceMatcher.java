package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class MandatorySequenceMatcher<C> implements SequenceMatcher<C> {
    private final SequenceMatcher<C> delegate;

    public MandatorySequenceMatcher(SequenceMatcher<C> delegate) {
        this.delegate = delegate;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult result = delegate.matches(sequenceMatcherRequest);

        if (!result.matched()) {
            return SequenceMatcherResult.error(sequenceMatcherRequest);
        }

        return result;
    }
}
