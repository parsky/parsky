package org.parsky.sequence;


import org.parsky.character.CharacterMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class CharacterSequenceMatcher<C> implements SequenceMatcher<C, String> {
    private final CharacterMatcher matcher;

    public CharacterSequenceMatcher(CharacterMatcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public SequenceMatcherResult<String> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        if (matcher.matches(sequenceMatcherRequest.getCurrentCharacter())) {
            return sequenceMatcherRequest.text(1);
        }

        return SequenceMatcherResult.mismatch();
    }
}
