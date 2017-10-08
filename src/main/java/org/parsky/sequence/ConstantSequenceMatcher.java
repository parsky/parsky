package org.parsky.sequence;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class ConstantSequenceMatcher<C, T> implements SequenceMatcher<C, T> {
    private final T value;

    public ConstantSequenceMatcher(T value) {
        this.value = value;
    }

    @Override
    public SequenceMatcherResult<T> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        return SequenceMatcherResult.match(0, new MatchResult<>(sequenceMatcherRequest.range(0), value));
    }
}
