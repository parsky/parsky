package org.parsky.sequence.transform;

import org.parsky.sequence.model.SequenceMatcherRequest;

public class ConstantTransformation implements Transformation {
    private final Object value;

    public ConstantTransformation(Object value) {
        this.value = value;
    }

    @Override
    public Result transform(SequenceMatcherRequest request, Object input) {
        return Result.success(value);
    }
}
