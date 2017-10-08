package org.parsky.sequence;

import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlattenSequenceMatcher<C, R> implements SequenceMatcher<C, List<R>> {
    private final SequenceMatcher<C, List<R>> sequenceMatcher;

    public FlattenSequenceMatcher(SequenceMatcher<C, List<R>> sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult<List<R>> matches(SequenceMatcherRequest<C> sequenceMatcherRequest) {
        SequenceMatcherResult<List<R>> result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            if (result.getMatchResult().getValue() != null) {
                List<R> flatResult = new ArrayList<>();
                if (result.getMatchResult().getValue() instanceof List) {
                    for (R value : result.getMatchResult().getValue()) {
                        if (value instanceof List) {
                            flatResult.addAll((Collection) value);
                        } else {
                            flatResult.add(value);
                        }
                    }
                } else {
                    flatResult.add((R) result.getMatchResult().getValue());
                }
                return result.withValue(flatResult);
            }
        }

        return result;
    }
}
