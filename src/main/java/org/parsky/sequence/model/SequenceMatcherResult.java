package org.parsky.sequence.model;

import com.google.common.base.Optional;

public class SequenceMatcherResult<T> {
    public static <T> SequenceMatcherResult<T> mismatch () {
        return new SequenceMatcherResult<T>(SequenceMatcherResultType.MISMATCH, 0, Optional.<MatchResult<T>>absent());
    }

    public static <T> SequenceMatcherResult<T> match (int jump, MatchResult<T> result) {
        return new SequenceMatcherResult<>(SequenceMatcherResultType.MATCHED, jump, Optional.of(result));
    }

    public static <T> SequenceMatcherResult<T> error(SequenceMatcherRequest sequenceMatcherRequest) {
        return new SequenceMatcherResult<>(SequenceMatcherResultType.ERROR, sequenceMatcherRequest.getOffset(), Optional.<MatchResult<T>>absent());
    }

    private final SequenceMatcherResultType type;
    private final int jump;
    private final Optional<MatchResult<T>> match;

    public SequenceMatcherResult(SequenceMatcherResultType type, int jump, Optional<MatchResult<T>> match) {
        this.type = type;
        this.jump = jump;
        this.match = match;
    }

    public boolean matched() {
        return type == SequenceMatcherResultType.MATCHED;
    }
    public boolean isMismatch() {
        return type == SequenceMatcherResultType.MISMATCH;
    }

    public int getJump() {
        return jump;
    }

    public SequenceMatcherResultType getType() {
        return type;
    }

    public boolean isError() {
        return type == SequenceMatcherResultType.ERROR;
    }

    public MatchResult<T> getMatchResult() {
        return match.get();
    }

    public SequenceMatcherResult<T> withJump(int jump) {
        return new SequenceMatcherResult<>(type, jump, match);
    }

    public <R> SequenceMatcherResult<R> withValue(R value) {
        return new SequenceMatcherResult<>(type, jump, Optional.of(match.get().with(value)));
    }

    public <T> SequenceMatcherResult<T> cast() {
        return (SequenceMatcherResult<T>) this;
    }
}
