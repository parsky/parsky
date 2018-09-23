package org.parsky.error;

import org.parsky.context.Context;
import org.parsky.grammar.Grammar;

public interface ErrorPrinter {
    String print (Request request);

    class Request {
        private final Grammar grammar;
        private final Context context;
        private final char[] content;
        private final int offset;
        private final String message;

        public Request(Grammar grammar, Context context, char[] content, int offset, String message) {
            this.grammar = grammar;
            this.context = context;
            this.content = content;
            this.offset = offset;
            this.message = message;
        }

        public Grammar getGrammar() {
            return grammar;
        }

        public Context getContext() {
            return context;
        }

        public char[] getContent() {
            return content;
        }

        public int getOffset() {
            return offset;
        }

        public String getMessage() {
            return message;
        }
    }
}
