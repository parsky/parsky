package org.parsky.sequence;


import org.parsky.character.CharacterMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class CharacterSequenceMatcher implements SequenceMatcher {
    private final CharacterMatcher matcher;

    public CharacterSequenceMatcher(CharacterMatcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        if (matcher.matches(sequenceMatcherRequest.getCurrentCharacter())) {
            return sequenceMatcherRequest.text(1);
        }

        return SequenceMatcherResult.mismatch();
    }
}
