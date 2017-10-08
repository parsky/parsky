package org.parsky.sequence.transform;


import org.parsky.sequence.model.Range;

public interface Transformation<I, O> {
    O transform(Range range, I input);
}
