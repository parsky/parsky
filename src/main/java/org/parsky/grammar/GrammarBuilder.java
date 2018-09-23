package org.parsky.grammar;

import org.parsky.sequence.LabelledSequenceMatcher;
import org.parsky.sequence.ReferenceSequenceMatcher;
import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;

import java.util.*;

public class GrammarBuilder {
    private final List<GrammarRuleDefinition> definitions = new ArrayList<>();

    public DefinitionBuilder define(String name) {
        return new DefinitionBuilder(this, Dependency.nameOf(name));
    }

    public DefinitionBuilder define(Class type) {
        return new DefinitionBuilder(this, Dependency.nameOf(type));
    }

    public Grammar startingOn (Class type) {
        return startingOn(Dependency.nameOf(type));
    }

    public Grammar startingOn(String mainRule) {
        Map<String, GrammarRuleDefinition> typeFactory = new HashMap<>();
        Map<String, Set<String>> dependencies = new HashMap<>();
        Map<String, ReferenceSequenceMatcher> referenceContainer = new HashMap<>();

        for (GrammarRuleDefinition definition : definitions) {
            if (typeFactory.containsKey(definition.getName()))
                throw new IllegalArgumentException(String.format("There is already a grammar rule %s", definition.getName()));

            typeFactory.put(definition.getName(), definition);
            dependencies.put(definition.getName(), extractChildDependenciesAndPopulateReferences(definition.getDependencies(), referenceContainer));
        }

        DependencyManager<String> manager = DependencyManager.create(dependencies);
        HashMap<String, SequenceMatcher> containerMap = new HashMap<>();
        Grammar grammar = new Grammar(mainRule, containerMap, referenceContainer);

        while (!manager.isEmpty()) {
            String name = manager.nextFreeFactory();
            GrammarRuleDefinition definition = typeFactory.get(name);
            SequenceMatcher sequenceMatcher = definition.getExpression().sequenceMatcher(grammar);
            if (!(sequenceMatcher instanceof LabelledSequenceMatcher)) {
                sequenceMatcher = new LabelledSequenceMatcher<>(definition.getName(), sequenceMatcher);
            }
            containerMap.put(name, sequenceMatcher);
            if (referenceContainer.containsKey(name)) {
                referenceContainer.get(name).assign(sequenceMatcher);
            }
        }
        return grammar;
    }

    private static <Context> Set<String> extractChildDependenciesAndPopulateReferences(Collection<Dependency> dependencies, Map<String, ReferenceSequenceMatcher> referenceContainer) {
        Set<String> result = new HashSet<>();
        for (Dependency dependency : dependencies) {
            if (!dependency.isParent()) {
                result.add(dependency.getName());
            } else {
                if (!referenceContainer.containsKey(dependency.getName())) {
                    referenceContainer.put(dependency.getName(), new ReferenceSequenceMatcher());
                }
            }
        }
        return result;
    }

    public static class DefinitionBuilder {
        private final GrammarBuilder builder;
        private final String name;

        public DefinitionBuilder(GrammarBuilder builder, String name) {
            this.builder = builder;
            this.name = name;
        }

        public GrammarBuilder asAnyOf (Object... list) {
            builder.definitions.add(new GrammarRuleDefinition(
                    name,
                    new AnyOfGrammarRuleExpression(Arrays.asList(list)),
                    Arrays.asList(Dependency.children(list))
            ));
            return builder;
        }

        public DefinitionBuilderDependencies as(GrammarRuleExpression expression) {
            return new DefinitionBuilderDependencies(
                    builder, name,
                    expression
            );
        }

        public DefinitionBuilderDependencies as(SequenceMatcher expression) {
            return new DefinitionBuilderDependencies(
                    builder, name,
                    new StaticGrammarRuleExpression(
                            new LabelledSequenceMatcher<>(
                                    name,
                                    expression
                            )
                    )
            );
        }
    }

    public static class StaticGrammarRuleExpression implements GrammarRuleExpression {
        private final SequenceMatcher sequenceMatcher;

        public StaticGrammarRuleExpression(SequenceMatcher sequenceMatcher) {
            this.sequenceMatcher = sequenceMatcher;
        }

        @Override
        public SequenceMatcher sequenceMatcher(Grammar context) {
            return sequenceMatcher;
        }
    }

    public static class AnyOfGrammarRuleExpression implements GrammarRuleExpression {
        private final List<Object> dependencies;

        public AnyOfGrammarRuleExpression(List<Object> dependencies) {
            this.dependencies = dependencies;
        }

        @Override
        public SequenceMatcher sequenceMatcher(Grammar context) {
            return SequenceMatchers.firstOf(context.sequenceMatchersFor(dependencies));
        }
    }

    public static class DefinitionBuilderDependencies {
        private final GrammarBuilder builder;
        private final String name;
        private final GrammarRuleExpression expression;

        public DefinitionBuilderDependencies(GrammarBuilder builder, String name, GrammarRuleExpression expression) {
            this.builder = builder;
            this.name = name;
            this.expression = expression;
        }

        public GrammarBuilder dependingOn(Dependency... dependencies) {
            builder.definitions.add(new GrammarRuleDefinition(
                    name,
                    expression,
                    Arrays.asList(dependencies)
            ));
            return builder;
        }

        public GrammarBuilder dependingOn(Object... dependencies) {
            builder.definitions.add(new GrammarRuleDefinition(
                    name,
                    expression,
                    Arrays.asList(Dependency.children(dependencies))
            ));
            return builder;
        }

        public GrammarBuilder and () {
            builder.definitions.add(new GrammarRuleDefinition(
                    name,
                    expression,
                    Collections.<Dependency>emptyList()
            ));
            return builder;
        }
    }
}
