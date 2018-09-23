package org.parsky.engine.value;

public class EmptyValueResult implements ValueResult {
    private static final EmptyValueResult EMPTY_VALUE_RESULT = new EmptyValueResult();

    public static EmptyValueResult empty () {
        return EMPTY_VALUE_RESULT;
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public ValueResult append(ValueResult value) {
        return value;
    }
}
