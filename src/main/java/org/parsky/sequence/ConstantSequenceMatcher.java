package org.parsky.sequence;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

public class ConstantSequenceMatcher<T> implements TypedSequenceMatcher<T> {
    private final T value;

    public ConstantSequenceMatcher(T value) {
        this.value = value;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        return SequenceMatcherResult.match(0, new MatchResult(sequenceMatcherRequest.range(0), new ContentNode<>(value)));
    }
}
