package org.parsky.sequence.transform;


import org.parsky.sequence.model.Range;

public interface Transformation<C> {
    Object transform(C context, Range range, Object input);
}
