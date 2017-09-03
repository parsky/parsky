package org.parsky.sequence.transform;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

public class LabelledTransformation<T> implements Transformation<T> {
    private final String label;
    private final Transformation<T> transformation;

    public LabelledTransformation(String label, Transformation<T> transformation) {
        this.label = label;
        this.transformation = transformation;
    }

    @Override
    public ContentNode<T> transform(MatchResult matchResult) {
        try {
            return transformation.transform(matchResult);
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
