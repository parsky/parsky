package org.parsky.sequence;


import com.google.common.collect.ImmutableList;
import org.parsky.character.CharacterMatcher;
import org.parsky.character.WhiteSpaceCharacterMatcher;
import org.parsky.sequence.transform.Transformation;

import java.util.List;

import static org.parsky.character.CharacterMatchers.whiteSpace;

public class SequenceMatchers {
    public static <C> SequenceMatcher<C, String> match (CharacterMatcher characterMatcher) {
        return new CharacterSequenceMatcher<>(characterMatcher);
    }

    public static <C> SequenceMatcher<C, String> match (String label, CharacterMatcher characterMatcher) {
        return new LabelledSequenceMatcher<>(label, SequenceMatchers.<C>match(characterMatcher));
    }

    public static <C> SequenceMatcher<C, String> string (String text) {
        return new StringSequenceMatcher<>(text);
    }

    public static <C> SequenceMatcher<C, String> string (String label, String text) {
        return new LabelledSequenceMatcher<>(label, SequenceMatchers.<C>string(text));
    }

    public static <C, R> SequenceMatcher<C, List<R>> sequence (SequenceMatcher<C, R> first, SequenceMatcher<C, R>... others) {
        return new ConsecutiveSequenceMatcher<>(ImmutableList.<SequenceMatcher<C, R>>builder().add(first).add(others).build());
    }

    public static <C, R> SequenceMatcher<C, List<R>> sequence (String label, SequenceMatcher<C, R> first, SequenceMatcher<C, R>... others) {
        return new LabelledSequenceMatcher<>(label, sequence(first, others));
    }

    public static <C, R> SequenceMatcher<C, List<R>> sequence (List<SequenceMatcher<C, R>> list) {
        return new ConsecutiveSequenceMatcher<>(list);
    }

    public static <C, R> SequenceMatcher<C, List<R>> sequence (String label, List<SequenceMatcher<C, R>> list) {
        return new LabelledSequenceMatcher<>(label, sequence(list));
    }

    public static <C, R> SequenceMatcher<C, R> mandatory(SequenceMatcher<C, R> sequenceMatcher) {
        return new MandatorySequenceMatcher<>(sequenceMatcher);
    }

    public static <C, R> SequenceMatcher<C, R> mandatory(String label, SequenceMatcher<C, R> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, mandatory(sequenceMatcher));
    }

    public static <C, R> SequenceMatcher<C, List<R>> zeroOrMore(SequenceMatcher<C, R> sequenceMatcher) {
        return new ZeroOrMoreSequenceMatcher<>(sequenceMatcher);
    }

    public static <C, R> SequenceMatcher<C, List<R>> zeroOrMore(String label, SequenceMatcher<C, R> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, zeroOrMore(sequenceMatcher));
    }

    public static <C> SequenceMatcher<C, Void> test (SequenceMatcher<C, ?> sequenceMatcher) {
        return new TestSequenceMatcher<C>(sequenceMatcher);
    }

    public static  <C> SequenceMatcher<C, Void> test (String label, SequenceMatcher<C, ?> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, new TestSequenceMatcher<>(sequenceMatcher));
    }

    public static <C> SequenceMatcher<C, String> until (SequenceMatcher<C, ?> sequenceMatcher) {
        return new UntilSequenceMatcher<>(sequenceMatcher);
    }

    public static <C> SequenceMatcher<C, String> until (String label, SequenceMatcher<C, ?> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, until(sequenceMatcher));
    }

    public static <C, R> SequenceMatcher<C, R> firstOf (SequenceMatcher<C, R> first, SequenceMatcher<C, R>... sequenceMatchers) {
        return new FirstOfSequenceMatcher<>(ImmutableList.<SequenceMatcher<C, R>>builder().add(first).add(sequenceMatchers).build());
    }

    public static <C, R> SequenceMatcher<C, R> firstOf (String label, SequenceMatcher<C, R> first, SequenceMatcher<C, R>... sequenceMatchers) {
        return new LabelledSequenceMatcher<>(label, firstOf(first, sequenceMatchers));
    }

    public static <C, R> SequenceMatcher<C, R> firstOf (List<SequenceMatcher<C, R>> sequenceMatchers) {
        return new FirstOfSequenceMatcher<>(sequenceMatchers);
    }

    public static <C, R> SequenceMatcher<C, R> firstOf (String label, List<SequenceMatcher<C, R>> sequenceMatchers) {
        return new LabelledSequenceMatcher<>(label, firstOf(sequenceMatchers));
    }

    public static <C> SequenceMatcher<C, String> not (SequenceMatcher<C, ?> sequenceMatcher) {
        return new NotSequenceMatcher<>(sequenceMatcher);
    }

    public static <C> SequenceMatcher<C, String> not (String label, SequenceMatcher<C, ?> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, not(sequenceMatcher));
    }

    public static <C, I, O> SequenceMatcher<C, O> transform (SequenceMatcher<C, I> sequenceMatcher, Transformation<C, I, O> transform) {
        return new TransformSequenceMatcher<>(sequenceMatcher, transform);
    }

    public static <C, I, O> SequenceMatcher<C, O> transform (String label, SequenceMatcher<C, I> sequenceMatcher, Transformation<C, I, O> transform) {
        return new LabelledSequenceMatcher<>(label, transform(sequenceMatcher, transform));
    }

    public static <C> SequenceMatcher<C, String> matchedText(SequenceMatcher<C, ? extends Object> sequenceMatcher) {
        return new MatchedTextSequenceMatcher<>(sequenceMatcher);
    }

    public static <C> SequenceMatcher<C, String> whitespaces () {
        return SequenceMatchers.matchedText(
                SequenceMatchers.zeroOrMore(
                        SequenceMatchers.<C>match(
                                whiteSpace()
                        )
                )
        );
    }

    public static <C, T> SequenceMatcher<C, T> skipWhitespaces (SequenceMatcher<C, T> sequenceMatcher) {
        return new SkipWhiteSpacesSequenceMatcher<>(WhiteSpaceCharacterMatcher.whitespace(), sequenceMatcher);
    }

    public static <C, T> SequenceMatcher<C, T> optional (SequenceMatcher<C, T> matcher) {
        return new OptionalSequenceMatcher<>(matcher);
    }

    public static <C, T> SequenceMatcher<C, T> optional (String label, SequenceMatcher<C, T> matcher) {
        return new LabelledSequenceMatcher<>(label, new OptionalSequenceMatcher<>(matcher));
    }

    public static <C, R> SequenceMatcher<C, List<R>> oneOrMore(SequenceMatcher<C, R> delegate) {
        return new OneOrMoreSequenceMatcher<>(delegate);
    }

    public static <C, R> SequenceMatcher<C, List<R>> oneOrMore(String label, SequenceMatcher<C, R> delegate) {
        return new LabelledSequenceMatcher<>(label, oneOrMore(delegate));
    }

    public static <C, T> SequenceMatcher<C, List<T>> flatten(SequenceMatcher<C, List<T>> sequenceMatcher) {
        return new FlattenSequenceMatcher<>(sequenceMatcher);
    }

    public static <C, T> ReferenceSequenceMatcher<C, T> reference () {
        return new ReferenceSequenceMatcher<>();
    }


    public static <C, T> ConstantSequenceMatcher<C, T> constant (T value) {
        return new ConstantSequenceMatcher<>(value);
    }
}
