package org.parsky.grammar;

import org.parsky.sequence.ReferenceSequenceMatcher;
import org.parsky.sequence.SequenceMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grammar {
    public static  GrammarBuilder builder () {
        return new GrammarBuilder();
    }

    private final String mainRule;
    private final Map<String, SequenceMatcher> sequenceMatchers;
    private final Map<String, ReferenceSequenceMatcher> referenceContainer;

    public Grammar(String mainRule, Map<String, SequenceMatcher> sequenceMatchers, Map<String, ReferenceSequenceMatcher> referenceContainer) {
        this.mainRule = mainRule;
        this.sequenceMatchers = sequenceMatchers;
        this.referenceContainer = referenceContainer;
    }

    public SequenceMatcher sequenceMatcherFor(Class dependency) {
        return sequenceMatcherFor(Dependency.nameOf(dependency));
    }

    public SequenceMatcher sequenceMatcherFor(String dependency) {
        SequenceMatcher matcher = sequenceMatchers.get(dependency);

        if (matcher == null) {
            if (referenceContainer.containsKey(dependency)) {
                return referenceContainer.get(dependency);
            }

            throw new IllegalStateException(String.format(
                    "No sequence matcher named %s found in the context. Did you forget to add it?",
                    dependency
            ));
        }

        return matcher;
    }

    public List<SequenceMatcher> sequenceMatchersFor(List<Object> references) {
        List<SequenceMatcher> sequenceMatchers = new ArrayList<>();
        for (Object reference : references) {
            sequenceMatchers.add(sequenceMatcherFor(Dependency.nameOf(reference)));
        }
        return sequenceMatchers;
    }

    public SequenceMatcher start () {
        return sequenceMatcherFor(mainRule);
    }
}
