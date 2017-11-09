package org.parsky.sequence;


import com.google.common.collect.ImmutableList;
import org.parsky.character.CharacterMatcher;
import org.parsky.character.WhiteSpaceCharacterMatcher;
import org.parsky.sequence.transform.Transformation;

import java.util.List;

import static org.parsky.character.CharacterMatchers.whiteSpace;

public class SequenceMatchers {
    public static <C> SequenceMatcher<C> match (CharacterMatcher characterMatcher) {
        return new CharacterSequenceMatcher<>(characterMatcher);
    }

    public static <C> SequenceMatcher<C> match (String label, CharacterMatcher characterMatcher) {
        return new LabelledSequenceMatcher<>(label, SequenceMatchers.<C>match(characterMatcher));
    }

    public static <C> SequenceMatcher<C> string (String text) {
        return new StringSequenceMatcher<>(text);
    }

    public static <C> SequenceMatcher<C> string (String label, String text) {
        return new LabelledSequenceMatcher<>(label, SequenceMatchers.<C>string(text));
    }

    public static <C> SequenceMatcher<C> sequence (SequenceMatcher<C> first, SequenceMatcher<C>... others) {
        return new ConsecutiveSequenceMatcher<>(ImmutableList.<SequenceMatcher<C>>builder().add(first).add(others).build());
    }

    public static <C> SequenceMatcher<C> sequence (String label, SequenceMatcher<C> first, SequenceMatcher<C>... others) {
        return new LabelledSequenceMatcher<>(label, sequence(first, others));
    }

    public static <C> SequenceMatcher<C> sequence (List<SequenceMatcher<C>> list) {
        return new ConsecutiveSequenceMatcher<>(list);
    }

    public static <C> SequenceMatcher<C> sequence (String label, List<SequenceMatcher<C>> list) {
        return new LabelledSequenceMatcher<>(label, sequence(list));
    }

    public static <C> SequenceMatcher<C> mandatory(SequenceMatcher<C> sequenceMatcher) {
        return new MandatorySequenceMatcher<>(sequenceMatcher);
    }

    public static <C> SequenceMatcher<C> mandatory(String label, SequenceMatcher<C> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, mandatory(sequenceMatcher));
    }

    public static <C> SequenceMatcher<C> zeroOrMore(SequenceMatcher<C> sequenceMatcher) {
        return new ZeroOrMoreSequenceMatcher<>(sequenceMatcher);
    }

    public static <C> SequenceMatcher<C> zeroOrMore(String label, SequenceMatcher<C> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, zeroOrMore(sequenceMatcher));
    }

    public static <C> SequenceMatcher<C> test (SequenceMatcher<C> sequenceMatcher) {
        return new TestSequenceMatcher<>(sequenceMatcher);
    }

    public static  <C> SequenceMatcher<C> test (String label, SequenceMatcher<C> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, new TestSequenceMatcher<>(sequenceMatcher));
    }

    public static <C> SequenceMatcher<C> until (SequenceMatcher<C> sequenceMatcher) {
        return new UntilSequenceMatcher<>(sequenceMatcher);
    }

    public static <C> SequenceMatcher<C> until (String label, SequenceMatcher<C> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, until(sequenceMatcher));
    }

    public static <C> SequenceMatcher<C> firstOf (SequenceMatcher<C> first, SequenceMatcher<C>... sequenceMatchers) {
        return new FirstOfSequenceMatcher<>(ImmutableList.<SequenceMatcher<C>>builder().add(first).add(sequenceMatchers).build());
    }

    public static <C, R> SequenceMatcher<C> firstOf (String label, SequenceMatcher<C> first, SequenceMatcher<C>... sequenceMatchers) {
        return new LabelledSequenceMatcher<>(label, firstOf(first, sequenceMatchers));
    }

    public static <C> SequenceMatcher<C> firstOf (List<SequenceMatcher<C>> sequenceMatchers) {
        return new FirstOfSequenceMatcher<>(sequenceMatchers);
    }

    public static <C> SequenceMatcher<C> firstOf (String label, List<SequenceMatcher<C>> sequenceMatchers) {
        return new LabelledSequenceMatcher<>(label, firstOf(sequenceMatchers));
    }

    public static <C> SequenceMatcher<C> not (SequenceMatcher<C> sequenceMatcher) {
        return new NotSequenceMatcher<>(sequenceMatcher);
    }

    public static <C> SequenceMatcher<C> not (String label, SequenceMatcher<C> sequenceMatcher) {
        return new LabelledSequenceMatcher<>(label, not(sequenceMatcher));
    }

    public static <C> SequenceMatcher<C> transform (SequenceMatcher<C> sequenceMatcher, Transformation<C> transform) {
        return new TransformSequenceMatcher<>(sequenceMatcher, transform);
    }

    public static <C> SequenceMatcher<C> transform (String label, SequenceMatcher<C> sequenceMatcher, Transformation<C> transform) {
        return new LabelledSequenceMatcher<>(label, transform(sequenceMatcher, transform));
    }

    public static <C> SequenceMatcher<C> matchedText(SequenceMatcher<C> sequenceMatcher) {
        return new MatchedTextSequenceMatcher<>(sequenceMatcher);
    }

    public static <C> SequenceMatcher<C> whitespaces () {
        return SequenceMatchers.matchedText(
                SequenceMatchers.zeroOrMore(
                        SequenceMatchers.<C>match(
                                whiteSpace()
                        )
                )
        );
    }

    public static <C> SequenceMatcher<C> skipWhitespaces (SequenceMatcher<C> sequenceMatcher) {
        return new SkipWhiteSpacesSequenceMatcher<>(WhiteSpaceCharacterMatcher.whitespace(), sequenceMatcher);
    }

    public static <C> SequenceMatcher<C> optional (SequenceMatcher<C> matcher) {
        return new OptionalSequenceMatcher<>(matcher);
    }

    public static <C> SequenceMatcher<C> optional (String label, SequenceMatcher<C> matcher) {
        return new LabelledSequenceMatcher<>(label, new OptionalSequenceMatcher<>(matcher));
    }

    public static <C> SequenceMatcher<C> oneOrMore(SequenceMatcher<C> delegate) {
        return new OneOrMoreSequenceMatcher<>(delegate);
    }

    public static <C> SequenceMatcher<C> oneOrMore(String label, SequenceMatcher<C> delegate) {
        return new LabelledSequenceMatcher<>(label, oneOrMore(delegate));
    }

    public static <C> SequenceMatcher<C> flatten(SequenceMatcher<C> sequenceMatcher) {
        return new FlattenSequenceMatcher<>(sequenceMatcher);
    }

    public static <C> ReferenceSequenceMatcher<C> reference () {
        return new ReferenceSequenceMatcher<>();
    }


    public static <C> ConstantSequenceMatcher<C> constant (Object value) {
        return new ConstantSequenceMatcher<>(value);
    }
}
