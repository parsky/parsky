package org.parsky.engine.simple.strategy;

import org.junit.Test;
import org.parsky.TestUtils;
import org.parsky.engine.ParserResult;
import org.parsky.engine.simple.SimpleParserEngine;
import org.parsky.grammar.rules.OneOrMoreRule;
import org.parsky.grammar.rules.Rules;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OneOrMoreParserEngineTest {
    private final OneOrMoreParserEngine underTest = new OneOrMoreParserEngine(SimpleParserEngine.create());

    @Test
    public void none() throws Exception {
        OneOrMoreRule rule = new OneOrMoreRule(Rules.string("b"));

        ParserResult result = underTest.apply(rule, TestUtils.request("aaa"));

        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.success(), is(false));
        assertThat(result.hasProgress(), is(false));
    }

    @Test
    public void one() throws Exception {
        OneOrMoreRule rule = new OneOrMoreRule(Rules.string("b"));

        ParserResult result = underTest.apply(rule, TestUtils.request("ba"));

        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.success(), is(true));
        assertThat(result.getSteps(), is(1));
        assertThat(result.getValue(), instanceOf(List.class));
        assertThat(result.hasProgress(), is(true));
    }

    @Test
    public void two() throws Exception {
        OneOrMoreRule rule = new OneOrMoreRule(Rules.string("b"));

        ParserResult result = underTest.apply(rule, TestUtils.request("bba"));

        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.success(), is(true));
        assertThat(result.getSteps(), is(2));
        assertThat(result.getValue(), instanceOf(List.class));
        assertThat(result.hasProgress(), is(true));
    }
}