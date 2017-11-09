package org.parsky.sequence;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.ArrayList;
import java.util.List;

public class ConsecutiveSequenceMatcher<C> implements SequenceMatcher<C> {
    private final List<SequenceMatcher<C>> sequenceMatchers;

    public ConsecutiveSequenceMatcher(List<SequenceMatcher<C>> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        int jump = 0;
        List<Object> nodes = new ArrayList<>();

        for (SequenceMatcher<C> sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest.incrementOffset(jump));

            if (result.isError()) return SequenceMatcherResult.error(sequenceMatcherRequest);
            if (!result.matched()) return SequenceMatcherResult.mismatch();

            jump += result.getJump();
            nodes.add(result.getMatchResult().getValue());
        }

        return SequenceMatcherResult.match(jump, new MatchResult(sequenceMatcherRequest.range(jump), nodes));
    }
}
