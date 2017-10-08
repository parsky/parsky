package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class UntilSequenceMatcher<C> implements SequenceMatcher<C, String> {
    private final SequenceMatcher<C, ?> sequenceMatcher;

    public UntilSequenceMatcher(SequenceMatcher<C, ?> sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult<String> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherRequest<C> request = sequenceMatcherRequest;
        SequenceMatcherResult<?> result = sequenceMatcher.matches(request);

        int jump = 0;

        while (result.isMismatch()) {
            jump++;
            request = request.incrementOffset(1);
            result = sequenceMatcher.matches(request);

            if (request.isEndOfInput()) break;
        }

        if (result.matched()) {
            return sequenceMatcherRequest.text(jump);
        } else {
            return result.cast();
        }
    }
}
