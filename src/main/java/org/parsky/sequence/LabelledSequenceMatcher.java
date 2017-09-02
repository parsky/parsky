package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class LabelledSequenceMatcher<T> implements TypedSequenceMatcher<T> {
    private final String label;
    private final SequenceMatcher sequenceMatcher;

    public LabelledSequenceMatcher(String label, SequenceMatcher sequenceMatcher) {
        this.label = label;
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        try {
            return sequenceMatcher.matches(sequenceMatcherRequest);
        } catch (RuntimeException e) {
            throw new LabelRuntimeException(String.format("Exception captured on sequence matcher %s", label), e);
        }
    }

    public static class LabelRuntimeException extends RuntimeException {
        public LabelRuntimeException(String message, Throwable cause) {
            super(message, cause);
        }
    }
 }
