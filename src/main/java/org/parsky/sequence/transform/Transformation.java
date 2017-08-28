package org.parsky.sequence.transform;


import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

public interface Transformation<T> {
    ContentNode<T> transform(MatchResult matchResult);
}
