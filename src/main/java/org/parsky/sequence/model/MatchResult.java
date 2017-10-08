package org.parsky.sequence.model;

public class MatchResult<T> {
    private final Range range;
    private final T value;

    public MatchResult(Range range, T value) {
        this.range = range;
        this.value = value;
    }

    public Range getRange() {
        return range;
    }

    public <R> MatchResult<R> with (R newValue) {
        return new MatchResult<>(range, newValue);
    }

    public T getValue() {
        return value;
    }
}
