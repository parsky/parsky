package org.parsky.sequence.infix;

import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.TypedSequenceMatcher;
import org.parsky.sequence.infix.configuration.CombinedExpressionFactory;
import org.parsky.sequence.infix.transform.CombinedExpressionTransformation;
import org.parsky.sequence.transform.Transformations;

public class InfixExpressionSequenceMatcher<Expression, InfixExpression> extends TransformSequenceMatcher<Expression> {
    public InfixExpressionSequenceMatcher(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory, TypedSequenceMatcher<Expression> expressionSequenceMatcher, TypedSequenceMatcher<InfixExpression> infixExpressionSequenceMatcher) {
        super(SequenceMatchers.flatten(
                SequenceMatchers.sequence(
                        expressionSequenceMatcher,
                        SequenceMatchers.flatten(
                                SequenceMatchers.zeroOrMore(
                                        SequenceMatchers.sequence(
                                                infixExpressionSequenceMatcher,
                                                expressionSequenceMatcher
                                        )
                                )
                        )
                )
        ), Transformations.fromContentList(new CombinedExpressionTransformation<>(combinedExpressionFactory)));
    }
}
