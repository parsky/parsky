package org.parsky;

import org.junit.Test;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.print.PrintParserEngine;
import org.parsky.grammar.Grammar;
import org.parsky.grammar.GrammarBuilder;
import org.parsky.grammar.infix.InfixCombiner;
import org.parsky.grammar.rules.transform.Transform;

import static java.util.Arrays.asList;
import static org.parsky.grammar.RuleFactories.reference;
import static org.parsky.grammar.RuleFactories.simple;
import static org.parsky.grammar.infix.InfixConfiguration.foldLeft;
import static org.parsky.grammar.infix.InfixRuleConfiguration.infixRule;
import static org.parsky.grammar.infix.InfixRuleFactory.infix;
import static org.parsky.grammar.rules.Rules.*;

public class ParskyTest {
    @Test
    public void name() throws Exception {
        Grammar grammar = GrammarBuilder.newGrammar()
                .define("number", transform(text(oneOrMore(range('0', '9'))), new Transform() {
                    @Override
                    public Result transform(ParserRequest request, Object input) {
                        return Result.success(new ValueExp(Integer.parseInt((String) input)));
                    }
                }))
                .define("binary", infix(foldLeft(
                        "binary",
                        reference("number"),
                        asList(
                                infixRule(10, simple(skipWhitespaces(constant("+", Op.SUM)))),
                                infixRule(10, simple(skipWhitespaces(constant("-", Op.SUBTRACT)))),
                                infixRule(5, simple(skipWhitespaces(constant("*", Op.MULT)))),
                                infixRule(5, simple(skipWhitespaces(constant("/", Op.DIV))))
                        ),
                        combineBinaryOperation(),
                        true
                )))
                .define("start", reference("binary"))
                .build("start");

        Parsky parser = Parsky.simple(grammar);

        System.out.println(PrintParserEngine.print(grammar));
        Exp result = (Exp) parser.parse("2+a*1/1");

        System.out.println(result.calc());
    }

    private InfixCombiner combineBinaryOperation() {
        return new InfixCombiner() {
            @Override
            public Transform.Result combine(ParserRequest request, Object leftOperand, Object operator, Object rightOperand) {
                return Transform.Result.success(new BinaryExp(
                        (Exp) leftOperand,
                        (Op) operator,
                        (Exp) rightOperand
                ));
            }
        };
    }

    enum Op {
        SUM(new Calc() {
            @Override
            public int calc(int left, int right) {
                return left + right;
            }
        }),
        SUBTRACT(new Calc() {
            @Override
            public int calc(int left, int right) {
                return left - right;
            }
        }),
        MULT(new Calc() {
            @Override
            public int calc(int left, int right) {
                return left * right;
            }
        }),
        DIV(new Calc() {
            @Override
            public int calc(int left, int right) {
                return left / right;
            }
        });

        private final Calc calc;

        Op(Calc calc) {
            this.calc = calc;
        }

        public Calc getCalc() {
            return calc;
        }
    }

    interface Calc {
        int calc(int left, int right);
    }

    interface Exp {
        int calc();
    }

    public static class ValueExp implements Exp {
        private final int value;

        public ValueExp(int value) {
            this.value = value;
        }

        @Override
        public int calc() {
            return value;
        }
    }

    public static class BinaryExp implements Exp {
        private final Exp left;
        private final Op operator;
        private final Exp right;

        public BinaryExp(Exp left, Op operator, Exp right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        public int calc() {
            return operator.getCalc().calc(left.calc(), right.calc());
        }
    }
}