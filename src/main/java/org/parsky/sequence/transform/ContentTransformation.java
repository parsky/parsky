package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.parsky.sequence.model.Range;

public class ContentTransformation<I, T> implements Transformation<I, T> {
    private final Function<Request<I>, T> function;

    public ContentTransformation(Function<Request<I>, T> function) {
        this.function = function;
    }

    @Override
    public T transform(Range range, I input) {
        return function.apply(new Request<I>(range, input));
    }

    public static class Request<V> {
        private final Range range;
        private final V value;

        public Request(Range range, V value) {
            this.range = range;
            this.value = value;
        }

        public Range getRange() {
            return range;
        }

        public V getValue() {
            return value;
        }
    }
}
