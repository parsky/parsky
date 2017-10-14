package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class IdentityTransformation<C, T> implements Transformation<C, T, T> {
    @Override
    public T transform(C context, Range range, T input) {
        return input;
    }
}
