package org.parsky.sequence;


import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ListNode;
import org.parsky.sequence.model.tree.Node;

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
        List<Node> nodes = new ArrayList<>();

        while (result.matched()) {
            nodes.add(result.getMatchResult().getNode());
            if (result.getJump() == 0)
                return SequenceMatcherResult.match(jump, result(sequenceMatcherRequest, jump, nodes));

            jump += result.getJump();
            SequenceMatcherRequest newRequest = sequenceMatcherRequest.incrementOffset(jump);

            if (newRequest.isEndOfInput())
                return SequenceMatcherResult.match(jump, result(sequenceMatcherRequest, jump, nodes));
            result = sequenceMatcher.matches(newRequest);
        }

        if (result.isError()) return result;

        return SequenceMatcherResult.match(jump, result(sequenceMatcherRequest, jump, nodes));
    }

    private MatchResult result(SequenceMatcherRequest sequenceMatcherRequest, int jump, List<Node> nodes) {
        return new MatchResult(sequenceMatcherRequest.range(jump), new ListNode(nodes));
    }
}
