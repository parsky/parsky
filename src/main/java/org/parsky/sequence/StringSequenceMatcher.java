package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class StringSequenceMatcher implements SequenceMatcher {
    private final String string;

    public StringSequenceMatcher(String string) {
        this.string = string;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        if (matchesString(sequenceMatcherRequest)) {
            return SequenceMatcherResult.match(string.length(), sequenceMatcherRequest.text(string.length()));
        }

        return SequenceMatcherResult.mismatch();
    }

    private boolean matchesString(SequenceMatcherRequest sequenceMatcherRequest) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (sequenceMatcherRequest.getCharacter(i) != chars[i])
                return false;
        }

        return true;
    }
}
