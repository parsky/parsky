package org.parsky.engine;

import com.google.common.base.Optional;
import org.parsky.context.Context;
import org.parsky.engine.value.ValueResult;

public class ParserRequest {

    private final char[] content;
    private final int offset;
    private final boolean testMode;
    private final Context context;

    public ParserRequest(char[] content, int offset, boolean testMode, Context context) {
        this.content = content;
        this.offset = offset;
        this.testMode = testMode;
        this.context = context;
    }

    public ParserRequest test() {
        return new ParserRequest(content, offset, true, context);
    }

    public ParserRequest step () {
        return new ParserRequest(
                content,
                offset + 1,
                testMode,
                context
        );
    }
    public ParserRequest plus (ParserResult result) {
        return new ParserRequest(
                content,
                offset + result.getSteps(),
                testMode,
                context
        );
    }

    public ParserResult ok(ValueResult value, int steps) {
        return new ParserResult(
                steps,
                Optional.of(value),
                Optional.<String>absent()
        );
    }
    public ParserResult ok(ValueResult value) {
        return ok(value, 0);
    }
    public ParserResult mismatch() {
        return new ParserResult(
                0,
                Optional.<ValueResult>absent(),
                Optional.<String>absent()
        );
    }
    public ParserResult error(String message) {
        return new ParserResult(
                0,
                Optional.<ValueResult>absent(),
                Optional.of(message)
        );
    }
    public ParserResult next(ValueResult value) {
        return new ParserResult(
                1,
                Optional.of(value),
                Optional.<String>absent()
        );
    }

    public char currentChar() {
        return content[offset];
    }

    public boolean hasCurrent() {
        return offset < content.length;
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

    public boolean isTestMode() {
        return testMode;
    }
}
