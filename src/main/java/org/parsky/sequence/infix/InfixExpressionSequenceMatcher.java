package org.parsky.sequence.infix;

import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.infix.configuration.CombinedExpressionFactory;
import org.parsky.sequence.infix.transform.CombinedExpressionTransformation;
import org.parsky.sequence.transform.Transformations;

public class InfixExpressionSequenceMatcher<Expression, InfixExpression> extends TransformSequenceMatcher {
    public InfixExpressionSequenceMatcher(CombinedExpressionFactory<Expression, InfixExpression> combinedExpressionFactory, SequenceMatcher expressionSequenceMatcher, SequenceMatcher infixExpressionSequenceMatcher) {
        super(SequenceMatchers.flatten(
                SequenceMatchers.sequence(
                        expressionSequenceMatcher,
                        SequenceMatchers.flatten(
                                SequenceMatchers.zeroOrMore(
                                        SequenceMatchers.sequence(
                                                infixExpressionSequenceMatcher,
                                                (SequenceMatcher) expressionSequenceMatcher
                                        )
                                )
                        )
                )
        ), Transformations.fromContentList(new CombinedExpressionTransformation<>(combinedExpressionFactory)));
    }
}
