package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.transform.Transformation;

public class TransformSequenceMatcher<C, I, O> implements SequenceMatcher<C, O> {
    private final SequenceMatcher<C, I> sequenceMatcher;
    private final Transformation<I, O> transformation;

    public TransformSequenceMatcher(SequenceMatcher<C, I> sequenceMatcher, Transformation<I, O> transformation) {
        this.sequenceMatcher = sequenceMatcher;
        this.transformation = transformation;
    }

    @Override
    public SequenceMatcherResult<O> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult<I> result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return result.withValue(transformation.transform(
                    result.getMatchResult().getRange(),
                    result.getMatchResult().getValue()
            ));
        }

        return result.cast();
    }
}
