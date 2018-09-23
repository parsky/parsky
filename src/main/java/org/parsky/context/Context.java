package org.parsky.context;

import com.google.common.base.Optional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

public class Context {
    private final HashMap<Class, Stack> stacks = new HashMap<>();

    public void put (Object item) {
        Stack stack = getStack(item.getClass());
        stack.add(item);
    }

    public <T> Optional<T> pop (Class<T> type) {
        Stack stack = getStack(type);
        if (stack.isEmpty()) return Optional.absent();
        return Optional.fromNullable(type.cast(stack.pop()));
    }

    public <T> Optional<T> peek (Class<T> type) {
        Stack stack = getStack(type);
        if (stack.isEmpty()) return Optional.absent();
        return Optional.fromNullable(type.cast(stack.peek()));
    }

    public <T> Collection<T> list (Class<T> type) {
        return getStack(type);
    }

    private Stack getStack(Class type) {
        if (!stacks.containsKey(type)) {
            stacks.put(type, new Stack());
        }
        return stacks.get(type);
    }
}
