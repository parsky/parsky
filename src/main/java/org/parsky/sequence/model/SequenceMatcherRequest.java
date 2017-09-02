package org.parsky.sequence.model;


import com.google.common.base.Optional;
import org.parsky.character.EndOfInputCharacterMatcher;
import org.parsky.sequence.model.tree.ContentNode;

public class SequenceMatcherRequest {
    private final char[] content;
    private final int offset;

    public SequenceMatcherRequest(char[] content, int offset) {
        this.content = content;
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public char getCharacter(int index) {
        if (index + offset >= content.length) return (char) -1;
        return content[index + offset];
    }

    public char getCurrentCharacter() {
        return getCharacter(0);
    }

    public SequenceMatcherRequest incrementOffset (int offset) {
        return new SequenceMatcherRequest(content, this.offset + offset);
    }

    public boolean isEndOfInput() {
        return getCurrentCharacter() == EndOfInputCharacterMatcher.EOI;
    }

    public MatchResult text(int jump) {
        return new MatchResult(range(jump), new ContentNode<>(new String(content, offset, Math.min(jump, content.length - offset))));
    }
    public SequenceMatcherResult empty () {
        return new SequenceMatcherResult(SequenceMatcherResultType.MATCHED, 0, Optional.of(text(0)));
    }

    public Range range (int jump) {
        return Range.range(offset, jump);
    }
}
