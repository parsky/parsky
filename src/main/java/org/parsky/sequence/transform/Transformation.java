package org.parsky.sequence.transform;


import org.parsky.sequence.model.Range;

public interface Transformation<C, I, O> {
    O transform(C context, Range range, I input);
}
