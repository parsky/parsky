package org.parsky.sequence.model.tree;

import java.util.List;

public class ListNode implements Node {
    private final List<Node> nodes;

    public ListNode(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
