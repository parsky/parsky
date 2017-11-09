package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.transform.Transformation;

public class TransformSequenceMatcher<C> implements SequenceMatcher<C> {
    private final SequenceMatcher<C> sequenceMatcher;
    private final Transformation<C> transformation;

    public TransformSequenceMatcher(SequenceMatcher<C> sequenceMatcher, Transformation<C> transformation) {
        this.sequenceMatcher = sequenceMatcher;
        this.transformation = transformation;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return result.withValue(transformation.transform(
                    sequenceMatcherRequest.getContext(),
                    result.getMatchResult().getRange(),
                    result.getMatchResult().getValue()
            ));
        }

        return result;
    }
}
