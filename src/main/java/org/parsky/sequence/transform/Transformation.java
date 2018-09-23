package org.parsky.sequence.transform;


import org.parsky.sequence.model.SequenceMatcherRequest;

public interface Transformation {
    Result transform(SequenceMatcherRequest context, Object input);

    class Result {
        public static Result success (Object result) {
            return new Result(result, null);
        }

        public static Result failure (String message) {
            return new Result(null, message);
        }

        private final Object result;
        private final String failureMessage;

        private Result(Object result, String failureMessage) {
            this.result = result;
            this.failureMessage = failureMessage;
        }

        public boolean isSuccess () {
            return failureMessage == null;
        }

        public Object getResult() {
            return result;
        }

        public String getFailureMessage() {
            return failureMessage;
        }
    }
}
