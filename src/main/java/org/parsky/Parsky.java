package org.parsky;

import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class Parsky<C, T> {
    private final SequenceMatcher<C, T> matcher;

    public Parsky(SequenceMatcher<C, T> matcher) {
        this.matcher = matcher;
    }

    public Result<T> parse (C context, String input) {
        SequenceMatcherRequest<C> request = new SequenceMatcherRequest<>(input.toCharArray(), 0, context);
        return new Result<>(matcher.matches(request));
    }

    public static class Result<T> {
        private final SequenceMatcherResult<T> result;

        public Result(SequenceMatcherResult<T> result) {
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

        public  T output () {
            return result.getMatchResult().getValue();
        }
    }
}
