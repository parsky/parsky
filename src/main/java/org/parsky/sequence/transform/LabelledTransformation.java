package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class LabelledTransformation<I, O> implements Transformation<I, O> {
    private final String label;
    private final Transformation<I, O> transformation;

    public LabelledTransformation(String label, Transformation<I, O> transformation) {
        this.label = label;
        this.transformation = transformation;
    }

    @Override
    public O transform(Range range, I input) {
        try {
            return transformation.transform(range, input);
        } catch (RuntimeException e) {
            throw new LabelRuntimeException(String.format("Exception captured on sequence matcher labelled '%s'", label), e);
        }
    }


    public static class LabelRuntimeException extends RuntimeException {
        public LabelRuntimeException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
