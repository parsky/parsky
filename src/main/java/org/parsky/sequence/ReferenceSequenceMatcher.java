package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceSequenceMatcher<C, R> implements SequenceMatcher<C, R> {
    private final AtomicReference<SequenceMatcher<C, R>> reference;

    public ReferenceSequenceMatcher() {
        this.reference = new AtomicReference<>();
    }

    @Override
    public SequenceMatcherResult<R> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        return reference.get().matches(sequenceMatcherRequest);
    }

    public <T extends SequenceMatcher<C, R>> T assign(T sequenceMatcher) {
        reference.set(sequenceMatcher);
        return sequenceMatcher;
    }
}
