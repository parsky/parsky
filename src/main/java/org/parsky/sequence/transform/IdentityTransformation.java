package org.parsky.sequence.transform;

import org.parsky.sequence.model.Range;

public class IdentityTransformation<C> implements Transformation<C> {
    @Override
    public Object transform(C context, Range range, Object input) {
        return input;
    }
}
