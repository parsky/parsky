package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.transform.Transformation;

public class TransformSequenceMatcher<T> implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;
    private final Transformation<T> transformation;

    public TransformSequenceMatcher(SequenceMatcher sequenceMatcher, Transformation<T> transformation) {
        this.sequenceMatcher = sequenceMatcher;
        this.transformation = transformation;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return result.withNode(transformation.transform(result.getMatchResult()));
        }

        return result;
    }
}
