package org.parsky.sequence;


import com.google.common.collect.ImmutableList;
import org.parsky.character.CharacterMatcher;
import org.parsky.character.WhiteSpaceCharacterMatcher;
import org.parsky.sequence.transform.Transformation;

import java.util.List;

import static org.parsky.character.CharacterMatchers.whiteSpace;

public class SequenceMatchers {
    public static SequenceMatcher match (CharacterMatcher characterMatcher) {
        return new CharacterSequenceMatcher(characterMatcher);
    }

    public static SequenceMatcher match (String label, CharacterMatcher characterMatcher) {
        return new LabelledSequenceMatcher(label, match(characterMatcher));
    }

    public static SequenceMatcher string (String text) {
        return new StringSequenceMatcher(text);
    }

    public static SequenceMatcher string (String label, String text) {
        return new LabelledSequenceMatcher(label, string(text));
    }

    public static SequenceMatcher sequence (SequenceMatcher first, SequenceMatcher... others) {
        return new ConsecutiveSequenceMatcher(ImmutableList.<SequenceMatcher>builder().add(first).add(others).build());
    }

    public static SequenceMatcher sequence (String label, SequenceMatcher first, SequenceMatcher... others) {
        return new LabelledSequenceMatcher(label, sequence(first, others));
    }

    public static SequenceMatcher sequence (List<SequenceMatcher> list) {
        return new ConsecutiveSequenceMatcher(list);
    }

    public static SequenceMatcher sequence (String label, List<SequenceMatcher> list) {
        return new LabelledSequenceMatcher(label, sequence(list));
    }

    public static SequenceMatcher mandatory(SequenceMatcher sequenceMatcher) {
        return new MandatorySequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher mandatory(String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, mandatory(sequenceMatcher));
    }

    public static SequenceMatcher zeroOrMore(SequenceMatcher sequenceMatcher) {
        return new ZeroOrMoreSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher zeroOrMore(String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, zeroOrMore(sequenceMatcher));
    }

    public static SequenceMatcher test (SequenceMatcher sequenceMatcher) {
        return new TestSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher test (String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, new TestSequenceMatcher(sequenceMatcher));
    }

    public static SequenceMatcher until (SequenceMatcher sequenceMatcher) {
        return new UntilSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher until (String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, until(sequenceMatcher));
    }

    public static SequenceMatcher firstOf (SequenceMatcher first, SequenceMatcher... sequenceMatchers) {
        return new FirstOfSequenceMatcher(ImmutableList.<SequenceMatcher>builder().add(first).add(sequenceMatchers).build());
    }

    public static SequenceMatcher firstOf (String label, SequenceMatcher first, SequenceMatcher... sequenceMatchers) {
        return new LabelledSequenceMatcher(label, firstOf(first, sequenceMatchers));
    }

    public static SequenceMatcher firstOf (List<SequenceMatcher> sequenceMatchers) {
        return new FirstOfSequenceMatcher(sequenceMatchers);
    }

    public static SequenceMatcher firstOf (String label, List<SequenceMatcher> sequenceMatchers) {
        return new LabelledSequenceMatcher(label, firstOf(sequenceMatchers));
    }

    public static SequenceMatcher not (SequenceMatcher sequenceMatcher) {
        return new NotSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher not (String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, not(sequenceMatcher));
    }

    public static <T> TypedSequenceMatcher<T> transform (SequenceMatcher sequenceMatcher, Transformation<T> transform) {
        return new TransformSequenceMatcher<>(sequenceMatcher, transform);
    }

    public static <T> TypedSequenceMatcher<T> transform (String label, SequenceMatcher sequenceMatcher, Transformation<T> transform) {
        return new LabelledSequenceMatcher<>(label, transform(sequenceMatcher, transform));
    }

    public static MatchedTextSequenceMatcher matchedText(SequenceMatcher sequenceMatcher) {
        return new MatchedTextSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher whitespaces () {
        return zeroOrMore(match(whiteSpace()));
    }

    public static <T> SkipWhiteSpacesSequenceMatcher<T> skipWhitespaces (SequenceMatcher sequenceMatcher) {
        return new SkipWhiteSpacesSequenceMatcher<>(WhiteSpaceCharacterMatcher.whitespace(), sequenceMatcher);
    }

    public static SequenceMatcher optional (SequenceMatcher matcher) {
        return new OptionalSequenceMatcher(matcher);
    }

    public static SequenceMatcher optional (String label, SequenceMatcher matcher) {
        return new LabelledSequenceMatcher<>(label, new OptionalSequenceMatcher(matcher));
    }

    public static SequenceMatcher oneOrMore(SequenceMatcher delegate) {
        return new OneOrMoreSequenceMatcher(delegate);
    }

    public static SequenceMatcher oneOrMore(String label, SequenceMatcher delegate) {
        return new LabelledSequenceMatcher<>(label, oneOrMore(delegate));
    }

    public static SequenceMatcher flatten(SequenceMatcher sequenceMatcher) {
        return new FlattenSequenceMatcher(sequenceMatcher);
    }

    public static <T> ReferenceSequenceMatcher<T> reference () {
        return new ReferenceSequenceMatcher<>();
    }

    public static <T> TypedSequenceMatcher<T> typed (SequenceMatcher sequenceMatcher) {
        return new CastTypedSequenceMatcher<>(sequenceMatcher);
    }
}
