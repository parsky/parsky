package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.transform.Transformation;

public class TransformSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;
    private final Transformation transformation;

    public TransformSequenceMatcher(SequenceMatcher sequenceMatcher, Transformation transformation) {
        this.sequenceMatcher = sequenceMatcher;
        this.transformation = transformation;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            Transformation.Result transformResult = transformation.transform(
                    sequenceMatcherRequest,
                    result.getMatchResult().getValue()
            );

            if (transformResult.isSuccess()) {
                return result.withValue(transformResult.getResult());
            } else {
                return sequenceMatcherRequest.error(transformResult.getFailureMessage());
            }
        }

        return result;
    }
}
