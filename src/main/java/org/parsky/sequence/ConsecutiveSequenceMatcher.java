package org.parsky.sequence;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.ArrayList;
import java.util.List;

public class ConsecutiveSequenceMatcher<C, R> implements SequenceMatcher<C, List<R>> {
    private final List<SequenceMatcher<C, R>> sequenceMatchers;

    public ConsecutiveSequenceMatcher(List<SequenceMatcher<C, R>> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public SequenceMatcherResult<List<R>> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        int jump = 0;
        List<R> nodes = new ArrayList<>();

        for (SequenceMatcher<C, R> sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult<R> result = sequenceMatcher.matches(sequenceMatcherRequest.incrementOffset(jump));

            if (result.isError()) return SequenceMatcherResult.error(sequenceMatcherRequest);
            if (!result.matched()) return SequenceMatcherResult.mismatch();

            jump += result.getJump();
            nodes.add(result.getMatchResult().getValue());
        }

        return SequenceMatcherResult.match(jump, new MatchResult<>(sequenceMatcherRequest.range(jump), nodes));
    }
}
