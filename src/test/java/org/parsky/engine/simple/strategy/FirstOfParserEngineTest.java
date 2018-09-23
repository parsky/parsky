package org.parsky.engine.simple.strategy;

import org.junit.Test;
import org.parsky.TestUtils;
import org.parsky.engine.ParserResult;
import org.parsky.engine.simple.SimpleParserEngine;
import org.parsky.grammar.rules.FirstOfRule;
import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.Rules;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FirstOfParserEngineTest {
    private FirstOfParserEngine underTest = new FirstOfParserEngine(SimpleParserEngine.create());

    @Test
    public void none() throws Exception {
        FirstOfRule rule = new FirstOfRule(Collections.<Rule>emptyList());

        ParserResult result = underTest.apply(rule, TestUtils.request("test"));

        assertThat(result.success(), is(false));
        assertThat(result.getSteps(), is(0));
        assertThat(result.getError().isPresent(), is(false));
    }

    @Test
    public void firstSuccess() throws Exception {
        FirstOfRule rule = new FirstOfRule(Arrays.asList(Rules.string("te"), Rules.string("e")));

        ParserResult result = underTest.apply(rule, TestUtils.request("test"));

        assertThat(result.success(), is(true));
        assertThat(result.getSteps(), is(2));
        assertThat(result.getError().isPresent(), is(false));
    }

    @Test
    public void secondSuccess() throws Exception {
        FirstOfRule rule = new FirstOfRule(Arrays.asList(Rules.string("tet"), Rules.string("t")));

        ParserResult result = underTest.apply(rule, TestUtils.request("test"));

        assertThat(result.success(), is(true));
        assertThat(result.getSteps(), is(1));
        assertThat(result.getError().isPresent(), is(false));
    }
}