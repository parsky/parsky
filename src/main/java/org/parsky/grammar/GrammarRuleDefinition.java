package org.parsky.grammar;

import java.util.List;

public class GrammarRuleDefinition {
    private final String name;
    private final GrammarRuleExpression expression;
    private final List<Dependency> dependencies;

    public GrammarRuleDefinition(String name, GrammarRuleExpression expression, List<Dependency> dependencies) {
        this.name = name;
        this.expression = expression;
        this.dependencies = dependencies;
    }

    public String getName() {
        return name;
    }

    public GrammarRuleExpression getExpression() {
        return expression;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
