package org.parsky.context;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private Map<Class, Stack> stacked = new HashMap<>();

    public <T> Optional<T> get (Class<T> type) {
        if (!stacked.containsKey(type))
            stacked.put(type, new Stack());

        if (stacked.get(type).isEmpty()) return Optional.absent();
        return Optional.of((T) stacked.get(type).peek());
    }

    public <T> Stack<T> tree (Class<T> type) {
        if (!stacked.containsKey(type))
            stacked.put(type, new Stack());
        return stacked.get(type);
    }

    public <T> void push (T context) {
        if (!stacked.containsKey(context.getClass()))
            stacked.put(context.getClass(), new Stack());

        stacked.get(context.getClass()).add(context);
    }

    public <T> Optional<T> pop (Class<T> type) {
        if (!stacked.containsKey(type))
            stacked.put(type, new Stack());

        if (stacked.get(type).isEmpty()) return Optional.absent();

        return Optional.of((T) stacked.get(type).pop());
    }
}
