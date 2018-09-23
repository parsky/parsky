package org.parsky.sequence;

import org.parsky.character.WhiteSpaceCharacterMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class SkipWhiteSpacesSequenceMatcher implements SequenceMatcher {
    private final WhiteSpaceCharacterMatcher whiteSpaceCharacterMatcher;
    private final SequenceMatcher sequenceMatcher;
    private final boolean before;
    private final boolean after;

    public SkipWhiteSpacesSequenceMatcher(WhiteSpaceCharacterMatcher whiteSpaceCharacterMatcher, SequenceMatcher sequenceMatcher, boolean before, boolean after) {
        this.whiteSpaceCharacterMatcher = whiteSpaceCharacterMatcher;
        this.sequenceMatcher = sequenceMatcher;
        this.before = before;
        this.after = after;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        int beforeJump = before ? skipWhiteSpaces(sequenceMatcherRequest) : 0;
        SequenceMatcherRequest request = sequenceMatcherRequest.incrementOffset(beforeJump);
        SequenceMatcherResult result = sequenceMatcher.matches(request);

        if (result.matched()) {
            int afterJump = after ? skipWhiteSpaces(request.incrementOffset(result.getJump())): 0;
            return result.withJump(beforeJump + result.getJump() + afterJump);
        }

        return result;
    }

    private int skipWhiteSpaces(SequenceMatcherRequest sequenceMatcherRequest) {
        int jump = 0;

        while (whiteSpaceCharacterMatcher.matches(sequenceMatcherRequest.getCharacter(jump))) {
            jump++;
        }

        return jump;
    }
}
