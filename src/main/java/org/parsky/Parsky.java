package org.parsky;

import org.parsky.sequence.TypedSequenceMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.model.tree.ContentNode;

public class Parsky<T> {
    private final Class<T> type;
    private final TypedSequenceMatcher<T> matcher;

    public Parsky(Class<T> type, TypedSequenceMatcher<T> matcher) {
        this.type = type;
        this.matcher = matcher;
    }

    public Result<T> parse (String input) {
        return new Result<>(type, matcher.matches(new SequenceMatcherRequest(input.toCharArray(), 0)));
    }

    public static class Result<T> {
        private final Class<T> type;
        private final SequenceMatcherResult result;

        public Result(Class<T> type, SequenceMatcherResult result) {
            this.type = type;
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
            return type.cast(((ContentNode) result.getMatchResult().getNode()).getContent());
        }
    }
}
