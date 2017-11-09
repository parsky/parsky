package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class LabelledTransformation<C> implements Transformation<C> {
    private final String label;
    private final Transformation<C> transformation;

    public LabelledTransformation(String label, Transformation<C> transformation) {
        this.label = label;
        this.transformation = transformation;
    }

    @Override
    public Object transform(C context, Range range, Object input) {
        try {
            return transformation.transform(context, range, input);
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
