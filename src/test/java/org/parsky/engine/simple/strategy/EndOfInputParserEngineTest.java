package org.parsky.engine.simple.strategy;

import org.junit.Test;
import org.parsky.TestUtils;
import org.parsky.engine.ParserRequest;
import org.parsky.engine.ParserResult;
import org.parsky.grammar.rules.EndOfInputRule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EndOfInputParserEngineTest {
    private final EndOfInputParserEngine underTest = new EndOfInputParserEngine();

    @Test
    public void notEndOfInput() throws Exception {
        EndOfInputRule rule = new EndOfInputRule();
        ParserRequest request = TestUtils.request("a");

        ParserResult result = underTest.apply(rule, request);

        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.hasProgress(), is(false));
        assertThat(result.success(), is(false));
    }

    @Test
    public void endOfInput() throws Exception {
        EndOfInputRule rule = new EndOfInputRule();
        ParserRequest request = TestUtils.request("");

        ParserResult result = underTest.apply(rule, request);

        assertThat(result.getError().isPresent(), is(false));
        assertThat(result.hasProgress(), is(false));
        assertThat(result.success(), is(true));
    }
}