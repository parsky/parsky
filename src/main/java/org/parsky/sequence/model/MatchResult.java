package org.parsky.sequence.model;

public class MatchResult {
    private final Range range;
    private final Object value;

    public MatchResult(Range range, Object value) {
        this.range = range;
        this.value = value;
    }

    public Range getRange() {
        return range;
    }

    public MatchResult with (Object newValue) {
        return new MatchResult(range, newValue);
    }

    public Object getValue() {
        return value;
    }

    public <T> T as (Class<T> type) {
        return type.cast(value);
    }
}
