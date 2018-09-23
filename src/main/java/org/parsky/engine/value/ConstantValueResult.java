package org.parsky.engine.value;

public class ConstantValueResult implements ValueResult {
    public static ConstantValueResult constant (Object value) {
        return new ConstantValueResult(value);
    }

    private final Object value;

    public ConstantValueResult(Object value) {
        this.value = value;
    }

    @Override
    public Object get() {
        return value;
    }

    @Override
    public ValueResult append(ValueResult value) {
        return this;
    }
}
