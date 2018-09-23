package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceSequenceMatcher implements SequenceMatcher {
    private final AtomicReference<SequenceMatcher> reference;

    public ReferenceSequenceMatcher() {
        this.reference = new AtomicReference<>();
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        return reference.get().matches(sequenceMatcherRequest);
    }

    public <T extends SequenceMatcher> T assign(T sequenceMatcher) {
        reference.set(sequenceMatcher);
        return sequenceMatcher;
    }
}
