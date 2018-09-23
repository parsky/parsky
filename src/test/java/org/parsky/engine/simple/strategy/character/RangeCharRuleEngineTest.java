package org.parsky.engine.simple.strategy.character;

import org.junit.Test;
import org.parsky.TestUtils;
import org.parsky.engine.ParserResult;
import org.parsky.engine.simple.strategy.CharacterParserEngine;
import org.parsky.grammar.rules.CharacterRule;
import org.parsky.grammar.rules.character.RangeCharRule;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RangeCharRuleEngineTest {
    private CharacterParserEngine underTest = new CharacterParserEngine(Collections.<Class, CharRuleEngine>singletonMap(
            RangeCharRule.class,
            new RangeCharRuleEngine()
    ));

    @Test
    public void beforeStart() throws Exception {
        CharacterRule rule = new CharacterRule(new RangeCharRule('c', 'e'));

        ParserResult result = underTest.apply(rule, TestUtils.request("a"));

        assertThat(result.success(), is(false));
        assertThat(result.getSteps(), is(0));
        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.hasProgress(), is(false));
    }

    @Test
    public void afterEnd() throws Exception {
        CharacterRule rule = new CharacterRule(new RangeCharRule('c', 'e'));

        ParserResult result = underTest.apply(rule, TestUtils.request("t"));

        assertThat(result.success(), is(false));
        assertThat(result.getSteps(), is(0));
        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.hasProgress(), is(false));
    }

    @Test
    public void atTheStart() throws Exception {
        CharacterRule rule = new CharacterRule(new RangeCharRule('c', 'e'));

        ParserResult result = underTest.apply(rule, TestUtils.request("c"));

        assertThat(result.success(), is(true));
        assertThat(result.getSteps(), is(1));
        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.hasProgress(), is(true));
    }

    @Test
    public void atTheEnd() throws Exception {
        CharacterRule rule = new CharacterRule(new RangeCharRule('c', 'e'));

        ParserResult result = underTest.apply(rule, TestUtils.request("e"));

        assertThat(result.success(), is(true));
        assertThat(result.getSteps(), is(1));
        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.hasProgress(), is(true));
    }

    @Test
    public void betweenStartAndEnd() throws Exception {
        CharacterRule rule = new CharacterRule(new RangeCharRule('c', 'e'));

        ParserResult result = underTest.apply(rule, TestUtils.request("d"));

        assertThat(result.success(), is(true));
        assertThat(result.getSteps(), is(1));
        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.hasProgress(), is(true));
    }
}