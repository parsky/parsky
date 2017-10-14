package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.parsky.sequence.model.Range;

public class ContentTransformation<C, I, T> implements Transformation<C, I, T> {
    private final Function<Request<C, I>, T> function;

    public ContentTransformation(Function<Request<C, I>, T> function) {
        this.function = function;
    }

    @Override
    public T transform(C context, Range range, I input) {
        return function.apply(new Request<C, I>(context, range, input));
    }

    public static class Request<C, V> {
        private final C context;
        private final Range range;
        private final V value;

        public Request(C context, Range range, V value) {
            this.context = context;
            this.range = range;
            this.value = value;
        }

        public C getContext() {
            return context;
        }

        public Range getRange() {
            return range;
        }

        public V getValue() {
            return value;
        }
    }
}
