package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.parsky.sequence.model.Range;

import java.util.List;

public class ListContentTransformation<I, T> implements Transformation<List<I>, T> {
    private final Function<Request<I>, T> function;

    public ListContentTransformation(Function<Request<I>, T> function) {
        this.function = function;
    }

    @Override
    public T transform(Range range, List<I> input) {
        return function.apply(new Request<I>(range, input));
    }

    public static class Request<I> {
        private final Range range;
        private final List<I> values;

        public Request(Range range, List<I> values) {
            this.range = range;
            this.values = values;
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

