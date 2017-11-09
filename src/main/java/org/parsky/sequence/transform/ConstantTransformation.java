package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class ConstantTransformation<C> implements Transformation<C> {
    private final Object value;

    public ConstantTransformation(Object value) {
        this.value = value;
    }

    @Override
    public Object transform(C context, Range range, Object input) {
        return value;
    }
}
