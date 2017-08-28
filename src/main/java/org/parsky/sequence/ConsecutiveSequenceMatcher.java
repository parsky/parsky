package org.parsky.sequence;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ListNode;
import org.parsky.sequence.model.tree.Node;

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
        List<Node> nodes = new ArrayList<>();

        for (SequenceMatcher sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest.incrementOffset(jump));

            if (result.isError()) return result;
            if (!result.matched()) return SequenceMatcherResult.<ListNode>mismatch();

            jump += result.getJump();
            nodes.add(result.getMatchResult().getNode());
        }

        return SequenceMatcherResult.match(jump, new MatchResult(sequenceMatcherRequest.range(jump), new ListNode(nodes)));
    }
}
