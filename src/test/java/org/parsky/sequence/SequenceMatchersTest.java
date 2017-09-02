package org.parsky.sequence;

import org.junit.Test;
import org.parsky.character.CharacterMatcher;
import org.parsky.sequence.transform.Transformation;

import java.util.Collections;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SequenceMatchersTest {
    @Test
    public void synonyms() throws Exception {

        assertThat(SequenceMatchers.match(mock(CharacterMatcher.class)), instanceOf(CharacterSequenceMatcher.class));
        assertThat(SequenceMatchers.string("string"), instanceOf(StringSequenceMatcher.class));
        assertThat(SequenceMatchers.mandatory(mock(SequenceMatcher.class)), instanceOf(MandatorySequenceMatcher.class));
        assertThat(SequenceMatchers.zeroOrMore(mock(SequenceMatcher.class)), instanceOf(ZeroOrMoreSequenceMatcher.class));
        assertThat(SequenceMatchers.test(mock(SequenceMatcher.class)), instanceOf(TestSequenceMatcher.class));
        assertThat(SequenceMatchers.until(mock(SequenceMatcher.class)), instanceOf(UntilSequenceMatcher.class));
        assertThat(SequenceMatchers.firstOf(mock(SequenceMatcher.class)), instanceOf(FirstOfSequenceMatcher.class));
        assertThat(SequenceMatchers.firstOf(Collections.singletonList(mock(SequenceMatcher.class))), instanceOf(FirstOfSequenceMatcher.class));
        assertThat(SequenceMatchers.sequence(Collections.singletonList(mock(SequenceMatcher.class))), instanceOf(ConsecutiveSequenceMatcher.class));
        assertThat(SequenceMatchers.sequence(mock(SequenceMatcher.class)), instanceOf(ConsecutiveSequenceMatcher.class));
        assertThat(SequenceMatchers.not(mock(SequenceMatcher.class)), instanceOf(NotSequenceMatcher.class));
        assertThat(SequenceMatchers.transform(mock(SequenceMatcher.class), mock(Transformation.class)), instanceOf(TransformSequenceMatcher.class));
        assertThat(SequenceMatchers.matchedText(mock(SequenceMatcher.class)), instanceOf(MatchedTextSequenceMatcher.class));
        assertThat(SequenceMatchers.whitespaces(), instanceOf(ZeroOrMoreSequenceMatcher.class));
        assertThat(SequenceMatchers.skipWhitespaces(mock(SequenceMatcher.class)), instanceOf(SkipWhiteSpacesSequenceMatcher.class));
        assertThat(SequenceMatchers.optional(mock(SequenceMatcher.class)), instanceOf(OptionalSequenceMatcher.class));
        assertThat(SequenceMatchers.oneOrMore(mock(SequenceMatcher.class)), instanceOf(OneOrMoreSequenceMatcher.class));
        assertThat(SequenceMatchers.flatten(mock(SequenceMatcher.class)), instanceOf(FlattenSequenceMatcher.class));
    }
}