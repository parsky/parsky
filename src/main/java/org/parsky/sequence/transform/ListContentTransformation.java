package org.parsky.sequence.transform;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import org.parsky.sequence.model.SequenceMatcherRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListContentTransformation implements Transformation {
    private final Function<Request, Result> function;

    public ListContentTransformation(Function<Request, Result> function) {
        this.function = function;
    }

    @Override
    public Result transform(SequenceMatcherRequest request, Object input) {
        if (input instanceof Collection) {
            return function.apply(new Request(request, new ArrayList<>((Collection) input)));
        }
        return function.apply(new Request(request, ImmutableList.of(input)));
    }

    public static class Request {
        private final SequenceMatcherRequest request;
        private final List<Object> values;

        public Request(SequenceMatcherRequest request, List<Object> values) {
            this.request = request;
            this.values = values;
        }

        public <T> T get (int index, Class<T> type) {
            return type.cast(values.get(index));
        }

        public <T> T get (int index) {
            return (T) values.get(index);
        }

        public int size () {
            return values.size();
        }

        public List<Object> getValues() {
            return values;
        }

        public SequenceMatcherRequest getSequenceMatcherRequest() {
            return request;
        }
    }

}

