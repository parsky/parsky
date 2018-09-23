package org.parsky.grammar.rules;

import com.google.common.collect.ImmutableList;
import org.parsky.engine.ParserRequest;
import org.parsky.grammar.rules.character.*;
import org.parsky.grammar.rules.transform.ListTransform;
import org.parsky.grammar.rules.transform.Transform;
import org.parsky.grammar.rules.transform.Transforms;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class Rules {

    public static Rule sequence (Rule... rules) {
        return sequence(asList(rules));
    }

    public static Rule sequence (List<Rule> rules) {
        return new SequenceRule(rules);
    }

    public static Rule firstOf (List<Rule> alternatives) {
        return new FirstOfRule(alternatives);
    }

    public static Rule firstOf (Rule... alternatives) {
        return firstOf(asList(alternatives));
    }

    public static Rule zeroOrMore (Rule rule) {
        return new ZeroOrMoreRule(rule);
    }

    public static Rule oneOrMore (Rule rule) {
        return new OneOrMoreRule(rule);
    }

    public static Rule optional (Rule rule) {
        return firstOf(rule, empty());
    }

    public static Rule empty () {
        return new EmptyRule();
    }

    public static Rule not (Rule rule) {
        return new NotRule(rule);
    }

    public static Rule test (Rule rule) {
        return new TestRule(rule);
    }

    public static Rule fail (String message) {
        return new FailRule(message);
    }

    public static Rule endOfInput () {
        return new EndOfInputRule();
    }

    public static Rule mandatory (Rule rule, String message) {
        return firstOf(rule, fail(message));
    }

    public static Rule transform (Rule rule, Transform transform) {
        return new TransformRule(rule, transform);
    }

    public static Rule constant (String string, Object value) {
        return transform(string(string), Transforms.value(value));
    }

    public static Rule flatten (Rule rule) {
        return transform(rule, Transforms.flatten());
    }

    public static Rule transform (Transform transform) {
        return transform(empty(), transform);
    }

    public static Rule skipWhitespaces (Rule rule) {
        return transform(
                sequence(
                        zeroOrMore(whitespace()),
                        rule,
                        zeroOrMore(whitespace())
                ),
                Transforms.pick(1)
        );
    }

    public static Rule skipWhitespacesBefore (Rule rule) {
        return transform(
                sequence(
                        zeroOrMore(whitespace()),
                        rule
                ),
                Transforms.pick(1)
        );
    }

    public static Rule skipWhitespacesAfter (Rule rule) {
        return transform(
                sequence(
                        rule,
                        zeroOrMore(whitespace())
                ),
                Transforms.pick(0)
        );
    }

    public static Rule list(Rule item, Rule start, Rule separator, Rule end) {
        return transform(
                sequence(
                        skipWhitespacesAfter(start),
                        optional(transform(
                                sequence(
                                        skipWhitespaces(item),
                                        zeroOrMore(transform(
                                                sequence(
                                                        separator,
                                                        skipWhitespaces(item)
                                                ),
                                                Transforms.pick(1)
                                        ))
                                ),
                                Transforms.list(new ListTransform.TransformList() {
                                    @Override
                                    public Transform.Result transform(ParserRequest request, ListTransform.Request input) {
                                        return Transform.Result.success(ImmutableList.builder()
                                                .add(input.get(0))
                                                .addAll((Iterable) input.get(1))
                                                .build());
                                    }
                                })
                        )),
                        end
                ),
                Transforms.list(new ListTransform.TransformList() {
                    @Override
                    public Transform.Result transform(ParserRequest request, ListTransform.Request input) {
                        return Transform.Result.success(input.get(1) == null ? emptyList() : input.get(1));
                    }
                })

        );
    }

    public static Rule string (String value) {
        return new StringRule(value);
    }

    public static Rule text (Rule rule) {
        return new CaptureTextRule(rule);
    }

    public static Rule whitespace () {
        return new CharacterRule(new WhitespaceCharRule());
    }

    public static Rule range (char start, char end) {
        return new CharacterRule(new RangeCharRule(start, end));
    }

    public static Rule anyOf (String possibilities) {
        return new CharacterRule(new AnyOfCharRule(possibilities.toCharArray()));
    }
    public static Rule noneOf (String possibilities) {
        return new CharacterRule(new NoneOfCharRule(possibilities.toCharArray()));
    }
    public static Rule character (char character) {
        return new CharacterRule(new SingleCharRule(character));
    }
}
