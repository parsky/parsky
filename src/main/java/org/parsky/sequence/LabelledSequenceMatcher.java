package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class LabelledSequenceMatcher<C> implements SequenceMatcher<C> {
    private final String label;
    private final SequenceMatcher<C> sequenceMatcher;

    public LabelledSequenceMatcher(String label, SequenceMatcher<C> sequenceMatcher) {
        this.label = label;
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        try {
            return sequenceMatcher.matches(sequenceMatcherRequest);
        } catch (RuntimeException e) {
            throw new LabelRuntimeException(String.format("Exception captured on sequence matcher labelled  '%s'", label), e);
        }
    }

    public static class LabelRuntimeException extends RuntimeException {
        public LabelRuntimeException(String message, Throwable cause) {
            super(message, cause);
        }
    }
 }
