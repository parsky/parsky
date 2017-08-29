package org.parsky.sequence.transform;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

public class CastTransformation<T> implements Transformation<T> {
    private final Class<T> type;

    public CastTransformation(Class<T> type) {
        this.type = type;
    }

    @Override
    public ContentNode<T> transform(MatchResult matchResult) {
        return new ContentNode<>(type.cast(((ContentNode) matchResult.getNode()).getContent()));
    }
}
