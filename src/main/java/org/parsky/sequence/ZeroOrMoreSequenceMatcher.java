package org.parsky.sequence;


import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.ArrayList;
import java.util.List;

public class ZeroOrMoreSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public ZeroOrMoreSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        int jump = 0;
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);
        List<Object> nodes = new ArrayList<>();

        while (result.matched()) {
            nodes.add(result.getMatchResult().getValue());
            if (result.getJump() == 0)
                return sequenceMatcherRequest.match(jump, nodes);

            jump += result.getJump();
            SequenceMatcherRequest newRequest = sequenceMatcherRequest.incrementOffset(jump);

            if (newRequest.isEndOfInput())
                return sequenceMatcherRequest.match(jump, nodes);

            result = sequenceMatcher.matches(newRequest);
        }

        if (result.isError()) return result;

        return sequenceMatcherRequest.match(jump, nodes);
    }
}
