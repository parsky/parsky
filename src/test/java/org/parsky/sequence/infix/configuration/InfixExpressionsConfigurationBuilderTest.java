package org.parsky.sequence.infix.configuration;

import org.junit.Test;
import org.parsky.sequence.TypedSequenceMatcher;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class InfixExpressionsConfigurationBuilderTest {
    @Test(expected = IllegalArgumentException.class)
    public void noFactory() throws Exception {
        InfixExpressionsConfigurationBuilder.infixExpressionsConfiguration().build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void noExpressionMatcher() throws Exception {
        InfixExpressionsConfigurationBuilder.infixExpressionsConfiguration()
                .withCombinedExpressionFactory(mock(CombinedExpressionFactory.class))
                .build();
    }
    @Test(expected = IllegalArgumentException.class)
    public void emptyList() throws Exception {
        InfixExpressionsConfigurationBuilder.infixExpressionsConfiguration()
                .withCombinedExpressionFactory(mock(CombinedExpressionFactory.class))
                .withExpressionParser(mock(TypedSequenceMatcher.class))
                .build();
    }

    @Test
    public void built() throws Exception {
        InfixExpressionsConfiguration result = InfixExpressionsConfigurationBuilder.infixExpressionsConfiguration()
                .withCombinedExpressionFactory(mock(CombinedExpressionFactory.class))
                .withExpressionParser(mock(TypedSequenceMatcher.class))
                .withInfix(mock(TypedSequenceMatcher.class), 1)
                .build();

        assertNotNull(result);
    }
}