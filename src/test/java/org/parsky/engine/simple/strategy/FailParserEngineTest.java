package org.parsky.engine.simple.strategy;

import org.junit.Test;
import org.parsky.TestUtils;
import org.parsky.engine.ParserResult;
import org.parsky.grammar.rules.FailRule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FailParserEngineTest {
    private FailParserEngine underTest = new FailParserEngine();

    @Test
    public void fail() throws Exception {
        ParserResult result = underTest.apply(new FailRule("message"), TestUtils.request("test"));

        assertThat(result.hasProgress(), is(false));
        assertThat(result.getError().isPresent(), is(true));
        assertThat(result.getError().get(), is("message"));
    }
}