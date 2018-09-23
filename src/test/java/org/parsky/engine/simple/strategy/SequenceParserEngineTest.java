package org.parsky.engine.simple.strategy;

import org.junit.Test;
import org.parsky.TestUtils;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.engine.simple.SimpleParserEngine;
import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.Rules;
import org.parsky.grammar.rules.SequenceRule;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class SequenceParserEngineTest {
    private final SequenceParserEngine underTest = new SequenceParserEngine(SimpleParserEngine.create());

    @Test
    public void none() throws Exception {
        SequenceRule rule = new SequenceRule(Collections.<Rule>emptyList());
        ParserRequest request = TestUtils.request("test");

        ParserResult result = underTest.apply(rule, request);

        assertThat(result.hasProgress(), is(false));
        assertThat(result.success(), is(true));
        assertThat(result.getValue(), instanceOf(List.class));
        assertThat(((Collection<?>) result.getValue()), hasSize(0));
    }

    @Test
    public void oneFail() throws Exception {
        SequenceRule rule = new SequenceRule(Collections.singletonList(Rules.string("a")));
        ParserRequest request = TestUtils.request("test");

        ParserResult result = underTest.apply(rule, request);

        assertThat(result.hasProgress(), is(false));
        assertThat(result.success(), is(false));
    }

    @Test
    public void oneSuccess() throws Exception {
        SequenceRule rule = new SequenceRule(Collections.singletonList(Rules.string("t")));
        ParserRequest request = TestUtils.request("test");

        ParserResult result = underTest.apply(rule, request);

        assertThat(result.hasProgress(), is(true));
        assertThat(result.getSteps(), is(1));
        assertThat(result.success(), is(true));
        assertThat(result.getValue(), instanceOf(List.class));
        assertThat(((Collection<?>) result.getValue()), hasSize(1));
    }

    @Test
    public void twoSuccess() throws Exception {
        SequenceRule rule = new SequenceRule(Arrays.asList(Rules.string("t"), Rules.string("e")));
        ParserRequest request = TestUtils.request("test");

        ParserResult result = underTest.apply(rule, request);

        assertThat(result.hasProgress(), is(true));
        assertThat(result.getSteps(), is(2));
        assertThat(result.success(), is(true));
        assertThat(result.getValue(), instanceOf(List.class));
        assertThat(((Collection<?>) result.getValue()), hasSize(2));
    }

    @Test
    public void twoFail() throws Exception {
        SequenceRule rule = new SequenceRule(Arrays.asList(Rules.string("t"), Rules.string("t")));
        ParserRequest request = TestUtils.request("test");

        ParserResult result = underTest.apply(rule, request);

        assertThat(result.hasProgress(), is(false));
        assertThat(result.getSteps(), is(0));
        assertThat(result.success(), is(false));
    }
}