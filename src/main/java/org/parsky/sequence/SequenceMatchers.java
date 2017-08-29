package org.parsky.sequence;


import com.google.common.collect.ImmutableList;
import org.parsky.character.CharacterMatcher;
import org.parsky.character.WhiteSpaceCharacterMatcher;
import org.parsky.sequence.transform.Transformation;

import java.util.List;

import static java.util.Arrays.asList;
import static org.parsky.character.CharacterMatchers.whiteSpace;

public class SequenceMatchers {
    public static CharacterSequenceMatcher match (CharacterMatcher characterMatcher) {
        return new CharacterSequenceMatcher(characterMatcher);
    }

    public static StringSequenceMatcher string (String text) {
        return new StringSequenceMatcher(text);
    }

    public static ConsecutiveSequenceMatcher sequence (SequenceMatcher first, SequenceMatcher... others) {
        return new ConsecutiveSequenceMatcher(ImmutableList.<SequenceMatcher>builder().add(first).add(others).build());
    }

    public static ConsecutiveSequenceMatcher sequence (List<SequenceMatcher> list) {
        return new ConsecutiveSequenceMatcher(list);
    }

    public static MandatorySequenceMatcher mandatory(SequenceMatcher sequenceMatcher) {
        return new MandatorySequenceMatcher(sequenceMatcher);
    }

    public static ZeroOrMoreSequenceMatcher zeroOrMore(SequenceMatcher sequenceMatcher) {
        return new ZeroOrMoreSequenceMatcher(sequenceMatcher);
    }

    public static TestSequenceMatcher test (SequenceMatcher sequenceMatcher) {
        return new TestSequenceMatcher(sequenceMatcher);
    }

    public static UntilSequenceMatcher until (SequenceMatcher sequenceMatcher) {
        return new UntilSequenceMatcher(sequenceMatcher);
    }

    public static FirstOfSequenceMatcher firstOf (SequenceMatcher first, SequenceMatcher... sequenceMatchers) {
        return new FirstOfSequenceMatcher(ImmutableList.<SequenceMatcher>builder().add(first).add(sequenceMatchers).build());
    }

    public static FirstOfSequenceMatcher firstOf (List<SequenceMatcher> sequenceMatchers) {
        return new FirstOfSequenceMatcher(sequenceMatchers);
    }

    public static NotSequenceMatcher not (SequenceMatcher sequenceMatcher) {
        return new NotSequenceMatcher(sequenceMatcher);
    }

    public static <T> TransformSequenceMatcher<T> transform (SequenceMatcher sequenceMatcher, Transformation<T> transform) {
        return new TransformSequenceMatcher<>(sequenceMatcher, transform);
    }

    public static FlattenSequenceMatcher flatten (SequenceMatcher sequenceMatcher) {
        return new FlattenSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher whitespaces () {
        return zeroOrMore(match(whiteSpace()));
    }

    public static SkipWhiteSpacesSequenceMatcher skipWhitespaces (SequenceMatcher sequenceMatcher) {
        return new SkipWhiteSpacesSequenceMatcher(WhiteSpaceCharacterMatcher.whitespace(), sequenceMatcher);
    }

    public static OptionalSequenceMatcher optional (SequenceMatcher matcher) {
        return new OptionalSequenceMatcher(matcher);
    }

    public static OneOrMoreSequenceMatcher oneOrMore(SequenceMatcher delegate) {
        return new OneOrMoreSequenceMatcher(delegate);
    }

    public static MergeSequenceMatcher merge (SequenceMatcher... list) {
        return new MergeSequenceMatcher(asList(list));
    }

    public static MergeSequenceMatcher merge (List<SequenceMatcher> list) {
        return new MergeSequenceMatcher(list);
    }
}
