package org.parsky.engine.simple.strategy;

import org.junit.Test;
import org.parsky.TestUtils;
import org.parsky.engine.ParserResult;
import org.parsky.engine.simple.SimpleParserEngine;
import org.parsky.grammar.rules.Rules;
import org.parsky.grammar.rules.TestRule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestParserEngineTest {
    private TestParserEngine underTest = new TestParserEngine(SimpleParserEngine.create());

    @Test
    public void testSuccess() throws Exception {
        TestRule rule = new TestRule(Rules.string("a"));

        ParserResult result = underTest.apply(rule, TestUtils.request("a"));

        assertThat(result.success(), is(true));
        assertThat(result.hasProgress(), is(false));
        assertThat(result.getError().isPresent(), is(false));
    }
}