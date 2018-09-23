package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class UntilSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public UntilSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherRequest request = sequenceMatcherRequest;
        SequenceMatcherResult result = sequenceMatcher.matches(request.testMode());

        int jump = 0;

        while (result.isMismatch()) {
            jump++;
            request = request.incrementOffset(1);
            result = sequenceMatcher.matches(request.testMode());

            if (request.isEndOfInput()) break;
        }

        if (result.matched()) {
            return sequenceMatcherRequest.text(jump);
        } else {
            return result;
        }
    }
}
