package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ListNode;
import org.parsky.sequence.model.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class FlattenSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public FlattenSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            if (result.getMatchResult().getNode() instanceof ListNode) {
                ListNode nodes = (ListNode) result.getMatchResult().getNode();
                List<Node> flatResult = new ArrayList<>();
                for (Node node : nodes.getNodes()) {
                    if (node instanceof ListNode) {
                        flatResult.addAll(((ListNode) node).getNodes());
                    } else {
                        flatResult.add(node);
                    }
                }
                return result.withNode(new ListNode(flatResult));
            }
        }

        return result;
    }
}
