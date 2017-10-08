package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class OptionalSequenceMatcher<C, R> implements SequenceMatcher<C, R> {
    private final SequenceMatcher<C, R> sequenceMatcher;

    public OptionalSequenceMatcher(SequenceMatcher<C, R> sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult<R> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult<R> result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.isMismatch()) return sequenceMatcherRequest.empty();

        return result;
    }
}
