package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class ConstantTransformation<V, T> implements Transformation<V, T> {
    private final T value;

    public ConstantTransformation(T value) {
        this.value = value;
    }

    @Override
    public T transform(Range range, Object input) {
        return value;
    }
}
