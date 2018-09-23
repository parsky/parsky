package org.parsky;

import org.parsky.context.Context;
import org.parsky.context.Label;
import org.parsky.grammar.Grammar;
import org.parsky.position.DefaultPositionDescriber;
import org.parsky.position.PositionDescriber;
import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class Parsky {
    private final SequenceMatcher matcher;

    public Parsky(SequenceMatcher matcher) {
        this.matcher = matcher;
    }
    public Parsky(Grammar grammar) {
        this.matcher = grammar.start();
    }

    public Result parse (String input) {
        Context context = new Context();
        SequenceMatcherRequest request = new SequenceMatcherRequest(input.toCharArray(), 0, context, false);
        return new Result(context, request, matcher.matches(request));
    }

    public <T> Result parse (T ctx, String input) {
        Context context = new Context();
        context.push(ctx);
        SequenceMatcherRequest request = new SequenceMatcherRequest(input.toCharArray(), 0, context, false);
        return new Result(context, request, matcher.matches(request));
    }

    public static class Result {
        private final Context context;
        private final SequenceMatcherRequest request;
        private final SequenceMatcherResult result;

        public Result(Context context, SequenceMatcherRequest request, SequenceMatcherResult result) {
            this.context = context;
            this.request = request;
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

        public String describeError (PositionDescriber describer) {
            if (isError()) {
                return String.format(
                        "Error: %s%n%s",
                        result.getError().getMessage(),
                        describer.explain(request.getContent(), result.getJump())
                );
            } else {
                return "No error";
            }
        }

        public Context getContext() {
            return context;
        }

        public String describeError () {
            return describeError(DefaultPositionDescriber.create(context.tree(Label.class)));
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
