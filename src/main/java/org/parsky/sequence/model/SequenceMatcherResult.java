package org.parsky.sequence.model;

import com.google.common.base.Optional;

public class SequenceMatcherResult {
    public static SequenceMatcherResult mismatch () {
        return new SequenceMatcherResult(SequenceMatcherResultType.MISMATCH, 0, Optional.<MatchResult>absent(), Optional.<ErrorResult>absent());
    }

    public static SequenceMatcherResult match (int jump, MatchResult result) {
        return new SequenceMatcherResult(SequenceMatcherResultType.MATCHED, jump, Optional.of(result), Optional.<ErrorResult>absent());
    }

    private final SequenceMatcherResultType type;
    private final int jump;
    private final Optional<MatchResult> match;
    private final Optional<ErrorResult> error;

    public SequenceMatcherResult(SequenceMatcherResultType type, int jump, Optional<MatchResult> match, Optional<ErrorResult> error) {
        this.type = type;
        this.jump = jump;
        this.match = match;
        this.error = error;
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

    public MatchResult getMatchResult() {
        return match.get();
    }

    public SequenceMatcherResult withJump(int jump) {
        return new SequenceMatcherResult(type, jump, match, error);
    }

    public <R> SequenceMatcherResult withValue(R value) {
        return new SequenceMatcherResult(type, jump, Optional.of(match.get().with(value)), error);
    }

    public ErrorResult getError() {
        return error.get();
    }
}
