package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class MandatorySequenceMatcher<C, T> implements SequenceMatcher<C, T> {
    private final SequenceMatcher<C, T> delegate;

    public MandatorySequenceMatcher(SequenceMatcher<C, T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public SequenceMatcherResult<T> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult<T> result = delegate.matches(sequenceMatcherRequest);

        if (!result.matched()) {
            return SequenceMatcherResult.error(sequenceMatcherRequest);
        }

        return result;
    }
}
