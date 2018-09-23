package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlattenSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public FlattenSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            if (result.getMatchResult().getValue() != null) {
                List<Object> flatResult = new ArrayList<>();
                if (result.getMatchResult().getValue() instanceof List) {
                    List list = (List) result.getMatchResult().getValue();
                    for (Object value : list) {
                        if (value instanceof List) {
                            flatResult.addAll((Collection) value);
                        } else {
                            flatResult.add(value);
                        }
                    }
                } else {
                    flatResult.add(result.getMatchResult().getValue());
                }
                return result.withValue(flatResult);
            }
        }

        return result;
    }
}
