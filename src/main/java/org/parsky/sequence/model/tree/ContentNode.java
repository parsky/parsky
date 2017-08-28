package org.parsky.sequence.model.tree;

public class ContentNode<T> implements Node {
    private final T content;

    public ContentNode(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
