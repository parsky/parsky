package org.parsky.sequence.model;


import org.parsky.sequence.model.tree.ContentNode;
import org.parsky.sequence.model.tree.Node;

public class MatchResult {
    private final Range range;
    private final Node node;

    public MatchResult(Range range, Node node) {
        this.range = range;
        this.node = node;
    }

    public Range getRange() {
        return range;
    }

    public Node getNode() {
        return node;
    }

    public MatchResult with (Node newNode) {
        return new MatchResult(range, newNode);
    }

    public <T> T content (Class<T> type) {
        return type.cast(((ContentNode) node).getContent());
    }
}
