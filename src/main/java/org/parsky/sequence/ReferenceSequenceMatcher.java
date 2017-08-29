package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceSequenceMatcher<T extends SequenceMatcher> implements SequenceMatcher {
    private final AtomicReference<T> reference;

    public ReferenceSequenceMatcher(AtomicReference<T> reference) {
        this.reference = reference;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        return reference.get().matches(sequenceMatcherRequest);
    }
}
