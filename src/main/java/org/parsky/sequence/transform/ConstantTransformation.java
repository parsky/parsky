package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class ConstantTransformation<C, V, T> implements Transformation<C, V, T> {
    private final T value;

    public ConstantTransformation(T value) {
        this.value = value;
    }

    @Override
    public T transform(C context, Range range, V input) {
        return value;
    }
}
