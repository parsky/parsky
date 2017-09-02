package org.parsky.sequence;

import org.parsky.character.WhiteSpaceCharacterMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class SkipWhiteSpacesSequenceMatcher<T> implements TypedSequenceMatcher<T> {
    private final WhiteSpaceCharacterMatcher whiteSpaceCharacterMatcher;
    private final SequenceMatcher sequenceMatcher;

    public SkipWhiteSpacesSequenceMatcher(WhiteSpaceCharacterMatcher whiteSpaceCharacterMatcher, SequenceMatcher sequenceMatcher) {
        this.whiteSpaceCharacterMatcher = whiteSpaceCharacterMatcher;
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        int beforeJump = skipWhiteSpaces(sequenceMatcherRequest);
        SequenceMatcherRequest request = sequenceMatcherRequest.incrementOffset(beforeJump);
        SequenceMatcherResult result = sequenceMatcher.matches(request);

        if (result.matched()) {
            int afterJump = skipWhiteSpaces(request.incrementOffset(result.getJump()));
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
