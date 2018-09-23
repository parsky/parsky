package org.parsky.sequence;

import org.parsky.context.Label;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

public class LabelledSequenceMatcher<C> implements SequenceMatcher {
    private final String label;
    private final SequenceMatcher sequenceMatcher;

    public LabelledSequenceMatcher(String label, SequenceMatcher sequenceMatcher) {
        this.label = label;
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        sequenceMatcherRequest.getContext().push(new Label(label));
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (!result.isError()) {
            sequenceMatcherRequest.getContext().pop(Label.class);
        }

        return result;
    }

}
