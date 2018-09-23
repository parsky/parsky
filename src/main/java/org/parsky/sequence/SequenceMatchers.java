package org.parsky.sequence;


import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import org.parsky.character.CharacterMatcher;
import org.parsky.character.CharacterMatchers;
import org.parsky.character.WhiteSpaceCharacterMatcher;
import org.parsky.sequence.transform.ListContentTransformation;
import org.parsky.sequence.transform.Transformation;
import org.parsky.sequence.transform.Transformations;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.parsky.character.CharacterMatchers.whiteSpace;

public class SequenceMatchers {
    public static SequenceMatcher match(CharacterMatcher characterMatcher) {
        return new CharacterSequenceMatcher(characterMatcher);
    }

    public static SequenceMatcher match(String label, CharacterMatcher characterMatcher) {
        return new LabelledSequenceMatcher(label, SequenceMatchers.match(characterMatcher));
    }

    public static SequenceMatcher string(String text) {
        return new StringSequenceMatcher(text);
    }

    public static SequenceMatcher string(String label, String text) {
        return new LabelledSequenceMatcher(label, SequenceMatchers.string(text));
    }

    public static SequenceMatcher endOfInput () {
        return new EndOfInputSequenceMatcher();
    }

    public static SequenceMatcher sequence(SequenceMatcher first, SequenceMatcher... others) {
        return new ConsecutiveSequenceMatcher(ImmutableList.<SequenceMatcher>builder().add(first).add(others).build());
    }

    public static SequenceMatcher sequence(String label, SequenceMatcher first, SequenceMatcher... others) {
        return new LabelledSequenceMatcher(label, sequence(first, others));
    }

    public static SequenceMatcher sequence(List<SequenceMatcher> list) {
        return new ConsecutiveSequenceMatcher(list);
    }

    public static SequenceMatcher sequence(String label, List<SequenceMatcher> list) {
        return new LabelledSequenceMatcher(label, sequence(list));
    }

    public static SequenceMatcher mandatory(SequenceMatcher sequenceMatcher, String errorMessage) {
        return new MandatorySequenceMatcher(sequenceMatcher, errorMessage);
    }

    public static SequenceMatcher mandatory(String label, SequenceMatcher sequenceMatcher, String errorMessage) {
        return new LabelledSequenceMatcher(label, mandatory(sequenceMatcher, errorMessage));
    }

    public static SequenceMatcher zeroOrMore(SequenceMatcher sequenceMatcher) {
        return new ZeroOrMoreSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher zeroOrMore(String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, zeroOrMore(sequenceMatcher));
    }

    public static SequenceMatcher test(SequenceMatcher sequenceMatcher) {
        return new TestSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher test(String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, new TestSequenceMatcher(sequenceMatcher));
    }

    public static SequenceMatcher until(SequenceMatcher sequenceMatcher) {
        return new UntilSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher until(String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, until(sequenceMatcher));
    }

    public static SequenceMatcher firstOf(SequenceMatcher first, SequenceMatcher... sequenceMatchers) {
        return new FirstOfSequenceMatcher(ImmutableList.<SequenceMatcher>builder().add(first).add(sequenceMatchers).build());
    }

    public static <C, R> SequenceMatcher firstOf(String label, SequenceMatcher first, SequenceMatcher... sequenceMatchers) {
        return new LabelledSequenceMatcher(label, firstOf(first, sequenceMatchers));
    }

    public static SequenceMatcher firstOf(List<SequenceMatcher> sequenceMatchers) {
        return new FirstOfSequenceMatcher(sequenceMatchers);
    }

    public static SequenceMatcher firstOf(String label, List<SequenceMatcher> sequenceMatchers) {
        return new LabelledSequenceMatcher(label, firstOf(sequenceMatchers));
    }

    public static SequenceMatcher not(SequenceMatcher sequenceMatcher) {
        return new NotSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher not(String label, SequenceMatcher sequenceMatcher) {
        return new LabelledSequenceMatcher(label, not(sequenceMatcher));
    }

    public static SequenceMatcher transform(SequenceMatcher sequenceMatcher, Transformation transform) {
        return new TransformSequenceMatcher(sequenceMatcher, transform);
    }

    public static SequenceMatcher transform(String label, SequenceMatcher sequenceMatcher, Transformation transform) {
        return new LabelledSequenceMatcher(label, transform(sequenceMatcher, transform));
    }

    public static SequenceMatcher matchedText(SequenceMatcher sequenceMatcher) {
        return new MatchedTextSequenceMatcher(sequenceMatcher);
    }

    public static SequenceMatcher whitespaces() {
        return SequenceMatchers.matchedText(
                SequenceMatchers.zeroOrMore(
                        SequenceMatchers.match(
                                whiteSpace()
                        )
                )
        );
    }

    public static SequenceMatcher skipWhitespaces(SequenceMatcher sequenceMatcher) {
        return new SkipWhiteSpacesSequenceMatcher(WhiteSpaceCharacterMatcher.whitespace(), sequenceMatcher, true, true);
    }

    public static SequenceMatcher skipWhitespacesAfter(SequenceMatcher sequenceMatcher) {
        return new SkipWhiteSpacesSequenceMatcher(WhiteSpaceCharacterMatcher.whitespace(), sequenceMatcher, false, true);
    }

    public static SequenceMatcher skipWhitespacesBefore(SequenceMatcher sequenceMatcher) {
        return new SkipWhiteSpacesSequenceMatcher(WhiteSpaceCharacterMatcher.whitespace(), sequenceMatcher, true, false);
    }

    public static SequenceMatcher optional(SequenceMatcher matcher) {
        return new OptionalSequenceMatcher(matcher);
    }

    public static SequenceMatcher optional(String label, SequenceMatcher matcher) {
        return new LabelledSequenceMatcher(label, new OptionalSequenceMatcher(matcher));
    }

    public static SequenceMatcher oneOrMore(SequenceMatcher delegate) {
        return new OneOrMoreSequenceMatcher(delegate);
    }

    public static SequenceMatcher oneOrMore(String label, SequenceMatcher delegate) {
        return new LabelledSequenceMatcher(label, oneOrMore(delegate));
    }

    public static SequenceMatcher flatten(SequenceMatcher sequenceMatcher) {
        return new FlattenSequenceMatcher(sequenceMatcher);
    }

    public static ReferenceSequenceMatcher reference () {
        return new ReferenceSequenceMatcher();
    }


    public static ConstantSequenceMatcher constant (Object value) {
        return new ConstantSequenceMatcher(value);
    }

    public static EmptySequenceMatcher empty () {
        return EmptySequenceMatcher.instance();
    }

    public static SequenceMatcher action (Transformation transformation) {
        return transform(
                SequenceMatchers.empty(),
                transformation
        );
    }

    public static SequenceMatcher fail (String message) {
        return new FailSequenceMatcher(message);
    }

    public static SequenceMatcher keyword (SequenceMatcher sequenceMatcher) {
        return SequenceMatchers.transform(
                SequenceMatchers.sequence(
                        sequenceMatcher,
                        SequenceMatchers.oneOrMore(SequenceMatchers.match(CharacterMatchers.whiteSpace()))
                ),
                Transformations.pick(0)
        );
    }

    public static SequenceMatcher list(SequenceMatcher item, SequenceMatcher start, SequenceMatcher separator, SequenceMatcher end) {
        return SequenceMatchers.transform(
                SequenceMatchers.sequence(
                        skipWhitespacesAfter(start),
                        optional(transform(
                                sequence(
                                        skipWhitespaces(item),
                                        zeroOrMore(transform(
                                                sequence(
                                                        separator,
                                                        skipWhitespaces(item)
                                                ),
                                                Transformations.pick(1)
                                        ))
                                ),
                                Transformations.fromContentList(new Function<ListContentTransformation.Request, Transformation.Result>() {
                                    @Override
                                    public Transformation.Result apply(ListContentTransformation.Request input) {
                                        return Transformation.Result.success(ImmutableList.builder()
                                                .add(input.get(0))
                                                .addAll((Iterable) input.get(1))
                                                .build());
                                    }
                                })
                        )),
                        end
                ),
                Transformations.fromContentList(new Function<ListContentTransformation.Request, Transformation.Result>() {
                    @Override
                    public Transformation.Result apply(ListContentTransformation.Request input) {
                        return Transformation.Result.success(input.get(1) == null ? emptyList() : input.get(1));
                    }
                })

        );
    }
}
