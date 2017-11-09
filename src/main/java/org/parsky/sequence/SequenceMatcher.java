package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public interface SequenceMatcher<Context> {
    SequenceMatcherResult matches(SequenceMatcherRequest<Context> sequenceMatcherRequest);
}
