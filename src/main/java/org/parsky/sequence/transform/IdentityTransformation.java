package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class IdentityTransformation<T> implements Transformation<T, T> {
    @Override
    public T transform(Range range, T input) {
        return input;
    }
}
