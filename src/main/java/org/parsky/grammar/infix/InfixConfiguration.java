package org.parsky.grammar.infix;

import org.parsky.grammar.RuleFactory;

import java.util.List;

public class InfixConfiguration {
    public static InfixConfiguration foldLeft(String namePrefix, RuleFactory elementRule, List<InfixRuleConfiguration> expressions, InfixCombiner combiner, boolean mandatorySecondOperand) {
        return new InfixConfiguration(
                namePrefix, elementRule, expressions,
                combiner, FoldType.FOLD_LEFT,
                mandatorySecondOperand);
    }

    public static InfixConfiguration foldRight(String namePrefix, RuleFactory elementRule, List<InfixRuleConfiguration> expressions, InfixCombiner combiner, boolean mandatorySecondOperand) {
        return new InfixConfiguration(
                namePrefix, elementRule, expressions,
                combiner, FoldType.FOLD_RIGHT,
                mandatorySecondOperand);
    }

    private final String name;
    private final RuleFactory elementRule;
    private final List<InfixRuleConfiguration> expressions;
    private final InfixCombiner combiner;
    private final FoldType foldType;
    private final boolean mandatorySecondOperand;

    protected InfixConfiguration(String name, RuleFactory elementRule, List<InfixRuleConfiguration> expressions, InfixCombiner combiner, FoldType foldType, boolean mandatorySecondOperand) {
        this.name = name;
        this.elementRule = elementRule;
        this.expressions = expressions;
        this.combiner = combiner;
        this.foldType = foldType;
        this.mandatorySecondOperand = mandatorySecondOperand;
    }

    public String getName() {
        return name;
    }

    protected List<InfixRuleConfiguration> getExpressions() {
        return expressions;
    }

    protected RuleFactory getElementRule() {
        return elementRule;
    }

    protected InfixCombiner getCombiner() {
        return combiner;
    }

    protected FoldType getFoldType() {
        return foldType;
    }

    public boolean isMandatorySecondOperand() {
        return mandatorySecondOperand;
    }

    protected enum FoldType {
        FOLD_LEFT,
        FOLD_RIGHT
    }
}
