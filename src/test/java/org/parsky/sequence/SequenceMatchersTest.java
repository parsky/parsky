package org.parsky.sequence;

import org.junit.Test;
import org.parsky.character.CharacterMatcher;
import org.parsky.sequence.model.SequenceMatcherRequest;
import org.parsky.sequence.model.SequenceMatcherResult;
import org.parsky.sequence.transform.Transformation;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SequenceMatchersTest {
    @Test
    public void synonyms() throws Exception {
        assertThat(SequenceMatchers.match(mock(CharacterMatcher.class)), instanceOf(CharacterSequenceMatcher.class));
        assertThat(SequenceMatchers.match("label", mock(CharacterMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.string("string"), instanceOf(StringSequenceMatcher.class));
        assertThat(SequenceMatchers.string("label", "string"), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.mandatory(mock(SequenceMatcher.class), "error"), instanceOf(MandatorySequenceMatcher.class));
        assertThat(SequenceMatchers.mandatory("label", mock(SequenceMatcher.class), "error"), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.zeroOrMore(mock(SequenceMatcher.class)), instanceOf(ZeroOrMoreSequenceMatcher.class));
        assertThat(SequenceMatchers.zeroOrMore("label", mock(SequenceMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.test(mock(SequenceMatcher.class)), instanceOf(TestSequenceMatcher.class));
        assertThat(SequenceMatchers.test("label", mock(SequenceMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.until(mock(SequenceMatcher.class)), instanceOf(UntilSequenceMatcher.class));
        assertThat(SequenceMatchers.until("label", mock(SequenceMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.firstOf(mock(SequenceMatcher.class)), instanceOf(FirstOfSequenceMatcher.class));
        assertThat(SequenceMatchers.firstOf("label", mock(SequenceMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.firstOf(Collections.<SequenceMatcher>singletonList(mock(SequenceMatcher.class))), instanceOf(FirstOfSequenceMatcher.class));
        assertThat(SequenceMatchers.firstOf("label", Collections.<SequenceMatcher>singletonList(mock(SequenceMatcher.class))), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.sequence(Collections.<SequenceMatcher>singletonList(mock(SequenceMatcher.class))), instanceOf(ConsecutiveSequenceMatcher.class));
        assertThat(SequenceMatchers.sequence("label", Collections.<SequenceMatcher>singletonList(mock(SequenceMatcher.class))), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.sequence(mock(SequenceMatcher.class)), instanceOf(ConsecutiveSequenceMatcher.class));
        assertThat(SequenceMatchers.sequence("label", mock(SequenceMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.not(mock(SequenceMatcher.class)), instanceOf(NotSequenceMatcher.class));
        assertThat(SequenceMatchers.not("label", mock(SequenceMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.transform(mock(SequenceMatcher.class), mock(Transformation.class)), instanceOf(TransformSequenceMatcher.class));
        assertThat(SequenceMatchers.transform("label", mock(SequenceMatcher.class), mock(Transformation.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.matchedText(mock(SequenceMatcher.class)), instanceOf(MatchedTextSequenceMatcher.class));
        assertThat(SequenceMatchers.whitespaces(), instanceOf(MatchedTextSequenceMatcher.class));
        assertThat(SequenceMatchers.skipWhitespaces(mock(SequenceMatcher.class)), instanceOf(SkipWhiteSpacesSequenceMatcher.class));
        assertThat(SequenceMatchers.optional(mock(SequenceMatcher.class)), instanceOf(OptionalSequenceMatcher.class));
        assertThat(SequenceMatchers.optional("label", mock(SequenceMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.oneOrMore(mock(SequenceMatcher.class)), instanceOf(OneOrMoreSequenceMatcher.class));
        assertThat(SequenceMatchers.oneOrMore("label", mock(SequenceMatcher.class)), instanceOf(LabelledSequenceMatcher.class));
        assertThat(SequenceMatchers.flatten(mock(SequenceMatcher.class)), instanceOf(FlattenSequenceMatcher.class));
        assertThat(SequenceMatchers.constant(2), instanceOf(ConstantSequenceMatcher.class));
    }

    @Test
    public void list() throws Exception {
        SequenceMatcher matcher = SequenceMatchers.list(SequenceMatchers.string("a"), SequenceMatchers.string("["), SequenceMatchers.string(","), SequenceMatchers.string("]"));

        SequenceMatcherResult result = matcher.matches(new SequenceMatcherRequest("[ a,a , a]".toCharArray(), 0, null, false));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getValue(), instanceOf(List.class));
        assertEquals(((List) result.getMatchResult().getValue()).get(0), "a");
        assertEquals(((List) result.getMatchResult().getValue()).get(1), "a");
        assertEquals(((List) result.getMatchResult().getValue()).get(2), "a");
    }

    @Test
    public void emptyList() throws Exception {
        SequenceMatcher matcher = SequenceMatchers.list(SequenceMatchers.string("a"), SequenceMatchers.string("["), SequenceMatchers.string(","), SequenceMatchers.string("]"));

        SequenceMatcherResult result = matcher.matches(new SequenceMatcherRequest("[   ]".toCharArray(), 0, null, false));

        assertThat(result.matched(), is(true));
        assertThat(result.getMatchResult().getValue(), instanceOf(List.class));
        assertThat(result.getMatchResult().getValue(), notNullValue());
        assertThat(((List) result.getMatchResult().getValue()).isEmpty(), is(true));
    }
}