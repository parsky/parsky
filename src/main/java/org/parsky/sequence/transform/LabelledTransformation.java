package org.parsky.sequence.transform;

import org.parsky.context.Label;
import org.parsky.sequence.model.SequenceMatcherRequest;

public class LabelledTransformation implements Transformation {
    private final String label;
    private final Transformation transformation;

    public LabelledTransformation(String label, Transformation transformation) {
        this.label = label;
        this.transformation = transformation;
    }

    @Override
    public Result transform(SequenceMatcherRequest request, Object input) {
        request.getContext().push(new Label(label));
        Result result = transformation.transform(request, input);

        if (result.isSuccess()) {
            request.getContext().pop(Label.class);
        }

        return result;
    }
}
