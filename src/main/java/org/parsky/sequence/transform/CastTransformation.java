package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class CastTransformation<C, T> implements Transformation<C, Object, T> {
    private final Class<T> type;

    public CastTransformation(Class<T> type) {
        this.type = type;
    }

    @Override
    public T transform(C context, Range range, Object input) {
        return type.cast(input);
    }
}
