package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.parsky.sequence.model.Range;

import java.util.List;

public class ListContentTransformation<C, I, T> implements Transformation<C, List<I>, T> {
    private final Function<Request<C, I>, T> function;

    public ListContentTransformation(Function<Request<C, I>, T> function) {
        this.function = function;
    }

    @Override
    public T transform(C context, Range range, List<I> input) {
        return function.apply(new Request<C, I>(context, range, input));
    }

    public static class Request<C, I> {
        private final C context;
        private final Range range;
        private final List<I> values;

        public Request(C context, Range range, List<I> values) {
            this.context = context;
            this.range = range;
            this.values = values;
        }

        public C getContext() {
            return context;
        }

        public <T extends I> T get (int index, Class<T> type) {
            return type.cast(values.get(index));
        }

        public <T extends I> T get (int index) {
            return (T) values.get(index);
        }

        public int size () {
            return values.size();
        }

        public List<I> getValues() {
            return values;
        }

        public Range getRange() {
            return range;
        }
    }

}

