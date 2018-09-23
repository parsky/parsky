package org.parsky.sequence.model;


import com.google.common.base.Optional;
import org.parsky.context.Context;

public class SequenceMatcherRequest {
    private final char[] content;
    private final int offset;
    private final boolean testMode;
    private final Context context;

    public SequenceMatcherRequest(char[] content, int offset, Context context, boolean testMode) {
        this.content = content;
        this.offset = offset;
        this.testMode = testMode;
        this.context = context;
    }

    public int getOffset() {
        return offset;
    }

    public Context getContext() {
        return context;
    }

    public char getCharacter(int index) {
        if (index + offset >= content.length) return (char) -1;
        return content[index + offset];
    }

    public boolean isTestMode() {
        return testMode;
    }

    public char getCurrentCharacter() {
        return getCharacter(0);
    }

    public SequenceMatcherRequest incrementOffset (int offset) {
        return new SequenceMatcherRequest(content, this.offset + offset, context, testMode);
    }

    public boolean isEndOfInput() {
        return getCurrentCharacter() == (char) -1;
    }

    public SequenceMatcherResult empty () {
        return new SequenceMatcherResult(SequenceMatcherResultType.MATCHED, 0, Optional.of(new MatchResult(range(0), null)), Optional.<ErrorResult>absent());
    }

    public SequenceMatcherResult text (int jump) {
        return new SequenceMatcherResult(SequenceMatcherResultType.MATCHED, jump, Optional.of(textResult(jump)), Optional.<ErrorResult>absent());
    }

    public SequenceMatcherResult error (String errorMessage) {
        return new SequenceMatcherResult(SequenceMatcherResultType.ERROR, 0, Optional.<MatchResult>absent(), Optional.of(new ErrorResult(errorMessage)));
    }


    private MatchResult textResult(int jump) {
        return new MatchResult(range(jump), new String(content, offset, Math.min(jump, content.length - offset)));
    }

    public Range range (int jump) {
        return Range.range(content, offset, jump);
    }

    public SequenceMatcherResult match(int jump, Object value) {
        return SequenceMatcherResult.match(jump, new MatchResult(
                range(jump),
                value
        ));
    }

    public SequenceMatcherRequest testMode() {
        return new SequenceMatcherRequest(
                content,
                offset,
                context,
                true
        );
    }

    public char[] getContent() {
        return content;
    }
}
