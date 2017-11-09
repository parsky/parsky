package org.parsky.sequence;


import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.ArrayList;
import java.util.List;

public class ZeroOrMoreSequenceMatcher<C> implements SequenceMatcher<C> {
    private final SequenceMatcher<C> sequenceMatcher;

    public ZeroOrMoreSequenceMatcher(SequenceMatcher<C> sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        int jump = 0;
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);
        List<Object> nodes = new ArrayList<>();

        while (result.matched()) {
            nodes.add(result.getMatchResult().getValue());
            if (result.getJump() == 0)
                return sequenceMatcherRequest.match(jump, nodes);

            jump += result.getJump();
            SequenceMatcherRequest<C> newRequest = sequenceMatcherRequest.incrementOffset(jump);

            if (newRequest.isEndOfInput())
                return sequenceMatcherRequest.match(jump, nodes);

            result = sequenceMatcher.matches(newRequest);
        }

        if (result.isError()) return result;

        return sequenceMatcherRequest.match(jump, nodes);
    }
}
