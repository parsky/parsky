package org.parsky.sequence.model;


import com.google.common.base.Optional;
import org.parsky.character.EndOfInputCharacterMatcher;

public class SequenceMatcherRequest<C> {
    private final char[] content;
    private final int offset;
    private final C context;

    public SequenceMatcherRequest(char[] content, int offset, C context) {
        this.content = content;
        this.offset = offset;
        this.context = context;
    }

    public int getOffset() {
        return offset;
    }

    public C getContext() {
        return context;
    }

    public char getCharacter(int index) {
        if (index + offset >= content.length) return (char) -1;
        return content[index + offset];
    }

    public char getCurrentCharacter() {
        return getCharacter(0);
    }

    public SequenceMatcherRequest<C> incrementOffset (int offset) {
        return new SequenceMatcherRequest<C>(content, this.offset + offset, context);
    }

    public boolean isEndOfInput() {
        return getCurrentCharacter() == EndOfInputCharacterMatcher.EOI;
    }

    public <T> SequenceMatcherResult<T> empty () {
        return new SequenceMatcherResult<T>(SequenceMatcherResultType.MATCHED, 0, Optional.of(new MatchResult<T>(range(0), null)));
    }

    public SequenceMatcherResult<String> text (int jump) {
        return new SequenceMatcherResult<>(SequenceMatcherResultType.MATCHED, jump, Optional.of(textResult(jump)));
    }

    private MatchResult<String> textResult(int jump) {
        return new MatchResult<>(range(jump), new String(content, offset, Math.min(jump, content.length - offset)));
    }

    public Range range (int jump) {
        return Range.range(offset, jump);
    }

    public <R> SequenceMatcherResult<R> match(int jump, R value) {
        return SequenceMatcherResult.match(jump, new MatchResult<R>(
                range(jump),
                value
        ));
    }
}
