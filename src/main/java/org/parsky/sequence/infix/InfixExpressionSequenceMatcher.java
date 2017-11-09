package org.parsky.sequence.infix;

import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.infix.configuration.CombinedExpressionFactory;
import org.parsky.sequence.infix.transform.CombinedExpressionTransformation;
import org.parsky.sequence.transform.Transformations;

public class InfixExpressionSequenceMatcher<Context, Expression, InfixExpression> extends TransformSequenceMatcher<Context> {
    public InfixExpressionSequenceMatcher(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory, SequenceMatcher<Context> expressionSequenceMatcher, SequenceMatcher<Context> infixExpressionSequenceMatcher) {
        super(SequenceMatchers.flatten(
                SequenceMatchers.sequence(
                        expressionSequenceMatcher,
                        SequenceMatchers.flatten(
                                SequenceMatchers.zeroOrMore(
                                        SequenceMatchers.sequence(
                                                infixExpressionSequenceMatcher,
                                                (SequenceMatcher<Context>) expressionSequenceMatcher
                                        )
                                )
                        )
                )
        ), Transformations.fromContentList(new CombinedExpressionTransformation<Context, Expression, InfixExpression>(combinedExpressionFactory)));
    }
}
