package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceSequenceMatcher<C> implements SequenceMatcher<C> {
    private final AtomicReference<SequenceMatcher<C>> reference;

    public ReferenceSequenceMatcher() {
        this.reference = new AtomicReference<>();
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        return reference.get().matches(sequenceMatcherRequest);
    }

    public <T extends SequenceMatcher<C>> T assign(T sequenceMatcher) {
        reference.set(sequenceMatcher);
        return sequenceMatcher;
    }
}
