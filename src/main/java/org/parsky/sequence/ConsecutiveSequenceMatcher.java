package org.parsky.sequence;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.ArrayList;
import java.util.List;

public class ConsecutiveSequenceMatcher implements SequenceMatcher {
    private final List<SequenceMatcher> sequenceMatchers;

    public ConsecutiveSequenceMatcher(List<SequenceMatcher> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        int jump = 0;
        List<Object> nodes = new ArrayList<>();

        for (SequenceMatcher sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest.incrementOffset(jump));
            jump += result.getJump();

            if (result.isError()) return result.withJump(jump);
            if (!result.matched()) return SequenceMatcherResult.mismatch();

            nodes.add(result.getMatchResult().getValue());
        }

        return SequenceMatcherResult.match(jump, new MatchResult(sequenceMatcherRequest.range(jump), nodes));
    }
}
