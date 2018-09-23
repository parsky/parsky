package org.parsky.engine.print.strategy;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.apache.commons.lang3.StringUtils;
import org.parsky.engine.print.PrintQueue;
import org.parsky.engine.print.PrintStrategy;
import org.parsky.engine.print.model.PrintNode;
import org.parsky.grammar.rules.FirstOfRule;
import org.parsky.grammar.rules.Rule;
import org.parsky.grammar.rules.SequenceRule;

import java.util.Collection;

public class FirstOfPrintStrategy implements PrintStrategy<FirstOfRule> {
    private final PrintStrategy printStrategy;

    public FirstOfPrintStrategy(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    @Override
    public PrintNode print(FirstOfRule rule, final PrintQueue queue) {
        Collection<String> strings = Collections2.transform(Collections2.filter(Collections2.transform(
                rule.getAlternatives(),
                new Function<Rule, PrintNode>() {
                    @Override
                    public PrintNode apply(Rule input) {
                        return printStrategy.print(input, queue);
                    }
                }), new Predicate<PrintNode>() {
            @Override
            public boolean apply(PrintNode input) {
                return !input.isIgnored();
            }
        }), new Function<PrintNode, String>() {
            @Override
            public String apply(PrintNode print) {
                if (print.isSimple()) return print.getContent();
                return String.format("(%s)", print.getContent());
            }
        });
        return new PrintNode(
                StringUtils.join(
                        strings,
                        " | "
                ),
                strings.size() < 2
        );
    }
}
