package org.parsky.grammar.rules.transform;

import org.parsky.engine.ParserRequest;

public interface Transform {
    Result transform(ParserRequest request, Object input);

    class Result {
        public static Result success (Object value) {
            return new Result(null, value);
        }
        public static Result fail (String message) {
            return new Result(message, null);
        }

        private final String error;
        private final Object value;

        private Result(String error, Object value) {
            this.error = error;
            this.value = value;
        }

        public boolean isSuccess () {
            return error == null;
        }

        public String getError() {
            return error;
        }

        public Object getValue() {
            return value;
        }
    }
}
