package org.parsky.sequence.transform;

import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

public class IdentityTransformation<T> implements Transformation<T> {
    @Override
    public ContentNode<T> transform(MatchResult matchResult) {
        return (ContentNode<T>) matchResult.getNode();
    }
}
