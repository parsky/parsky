package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.transform.context.TransformContext;

public class ContentTransformation<I> implements Transformation {
    private final Function<Request<I>, Result> function;

    public ContentTransformation(Function<Request<I>, Result> function) {
        this.function = function;
    }

    @Override
    public Result transform(SequenceMatcherRequest request, Object input) {
        return function.apply(new Request<>(request, (I) input));
    }

    public static class Request<V> {
        private final SequenceMatcherRequest request;
        private final V value;


        public Request(SequenceMatcherRequest request, V value) {
            this.request = request;
            this.value = value;
        }

        public SequenceMatcherRequest getSequenceMatcherRequest() {
            return request;
        }

        public V getValue() {
            return value;
        }
    }
}
