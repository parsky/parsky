package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.parsky.sequence.model.MatchResult;
import org.parsky.sequence.model.tree.ContentNode;

public class ContentTransformation<I, T> implements Transformation<T> {
    private final Function<Request<I>, T> function;

    public ContentTransformation(Function<Request<I>, T> function) {
        this.function = function;
    }

    @Override
    public ContentNode<T> transform(MatchResult matchResult) {
        return new ContentNode<>(function.apply(new Request<>(
                matchResult,
                ((ContentNode<I>) matchResult.getNode()).getContent()
        )));
    }

    public static class Request<V> {
        private final MatchResult matchResult;
        private final V value;

        public Request(MatchResult matchResult, V value) {
            this.matchResult = matchResult;
            this.value = value;
        }

        public MatchResult getMatchResult() {
            return matchResult;
        }

        public V getValue() {
            return value;
        }
    }
}
