package org.parsky.sequence.infix;

import com.google.common.base.Function;
import org.junit.Test;
import org.parsky.character.CharacterMatchers;
import org.parsky.sequence.SequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.SequentTestUtils;
import org.parsky.context.Context;
import org.parsky.sequence.infix.configuration.CombinedExpressionFactory;
import org.parsky.sequence.infix.configuration.InfixExpressionsConfigurationBuilder;
import org.parsky.sequence.model.Range;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.transform.ContentTransformation;
import org.parsky.sequence.transform.Transformation;
import org.parsky.sequence.transform.Transformations;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class InfixExpressionsSequenceMatcherFactoryTest {
    @Test
    public void integrationTest() throws Exception {
        SequenceMatcher binaryOperation = new InfixExpressionsSequenceMatcher<>(
                InfixExpressionsConfigurationBuilder.<Expression, Operator>infixExpressionsConfiguration()
                        .withCombinedExpressionFactory(
                                new CombinedExpressionFactory<Expression, Operator>() {
                                    @Override
                                    public Expression create(SequenceMatcherRequest request, Expression left, Operator infix, Expression right) {
                                        return new BinaryOperation(left, infix, right);
                                    }
                                }
                        )
                        .withExpressionParser(SequenceMatchers.transform("expression", SequenceMatchers.skipWhitespaces(SequenceMatchers.matchedText(
                                SequenceMatchers.oneOrMore(SequenceMatchers.match(CharacterMatchers.range('0', '9')))
                                )), Transformations.fromString(new Function<ContentTransformation.Request<String>, Transformation.Result>() {
                                    @Override
                                    public Transformation.Result apply(ContentTransformation.Request<String> input) {
                                        return Transformation.Result.success(new Number(Integer.parseInt(input.getValue())));
                                    }
                                }))
                        )
                        .withInfix("+", Operator.SUM, 1)
                        .withInfix("-", Operator.SUB, 1)
                        .withInfix("*", Operator.MULT, 0)
                        .withInfix(":", Operator.DIV, 0)
                        .build());

        SequenceMatcherResult result = binaryOperation.matches(SequentTestUtils.request("3 : 4 - 3 + 91 * 12"));

        assertThat(((BinaryOperation) result.getMatchResult().getValue()).left, instanceOf(BinaryOperation.class));
        assertThat(((BinaryOperation) result.getMatchResult().getValue()).operator, is(Operator.SUB));
        assertThat(((BinaryOperation) result.getMatchResult().getValue()).right, instanceOf(BinaryOperation.class));
    }

    public enum Operator {
        SUM,
        MULT,
        DIV,
        SUB
    }

    interface Expression {
    }

    private static class Number implements Expression {
        private final int number;

        public Number(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return reflectionToString(this);
        }
    }

    private static class BinaryOperation implements Expression {
        private final Expression left;
        private final Operator operator;
        private final Expression right;

        public BinaryOperation(Expression left, Operator operator, Expression right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        public String toString() {
            return reflectionToString(this);
        }
    }
}