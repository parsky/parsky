package org.parsky.sequence.transform;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

public class ConstantTransformation<T> implements Transformation<T> {
    private final T value;

    public ConstantTransformation(T value) {
        this.value = value;
    }

    @Override
    public ContentNode<T> transform(MatchResult matchResult) {
        return new ContentNode<>(value);
    }
}
