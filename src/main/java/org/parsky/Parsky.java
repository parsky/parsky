package org.parsky;

import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class Parsky<C> {
    private final SequenceMatcher<C> matcher;

    public Parsky(SequenceMatcher<C> matcher) {
        this.matcher = matcher;
    }

    public Result parse (C context, String input) {
        SequenceMatcherRequest<C> request = new SequenceMatcherRequest<>(input.toCharArray(), 0, context);
        return new Result(matcher.matches(request));
    }

    public static class Result {
        private final SequenceMatcherResult result;

        public Result(SequenceMatcherResult result) {
            this.result = result;
        }

        public boolean isError () {
            return result.isError();
        }

        public int getOffset () {
            return result.getJump();
        }

        public boolean isSuccess () {
            return result.matched();
        }

        public Object output () {
            return result.getMatchResult().getValue();
        }

        public <T> T as () {
            return (T) result.getMatchResult().getValue();
        }
        public <T> T as (Class<T> type) {
            return type.cast(result.getMatchResult().getValue());
        }
    }
}
