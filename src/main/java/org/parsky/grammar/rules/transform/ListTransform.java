package org.parsky.grammar.rules.transform;

import org.parsky.ParskyException;
import org.parsky.engine.ParserRequest;

import java.util.Iterator;
import java.util.List;

public class ListTransform implements Transform {
    private final TransformList transformList;

    public ListTransform(TransformList transformList) {
        this.transformList = transformList;
    }

    @Override
    public Result transform(ParserRequest request, Object input) {
        if (!(input instanceof List)) {
            throw new IllegalArgumentException(String.format("Expecting a list but found %s", input));
        }
        return transformList.transform(request, new Request((List) input));
    }

    public interface TransformList {
        Result transform(ParserRequest request, Request input);
    }

    public static class Request implements Iterable<Object> {
        private final List list;

        public Request(List list) {
            this.list = list;
        }

        public <T> T get (int index) {
            return (T) list.get(index);
        }

        public <T> T get (int index, Class<T> type) {
            return type.cast(list.get(index));
        }

        public List getList() {
            return list;
        }

        public int size() {
            return list.size();
        }

        @Override
        public Iterator<Object> iterator() {
            return list.iterator();
        }
    }
}
