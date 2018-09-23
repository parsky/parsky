package org.parsky.engine.simple.strategy;

import org.junit.Test;
import org.parsky.TestUtils;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.grammar.rules.EmptyRule;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EmptyParserEngineTest {
    private EmptyParserEngine underTest = new EmptyParserEngine();

    @Test
    public void empty() throws Exception {
        ParserRequest request = TestUtils.request("asd");
        EmptyRule rule = new EmptyRule();

        ParserResult result = underTest.apply(rule, request);

        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.success(), is(true));
        assertThat(result.hasProgress(), is(false));
        assertThat(result.getValue(), nullValue());
        assertThat(result.getSteps(), is(0));
    }
}