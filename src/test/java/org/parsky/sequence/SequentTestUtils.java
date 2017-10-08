package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;

public class SequentTestUtils {
    public static SequenceMatcherRequest<Object> request (String input) {
        return new SequenceMatcherRequest<>(input.toCharArray(), 0, new Object());
    }
}
