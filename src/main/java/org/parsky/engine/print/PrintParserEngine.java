package org.parsky.engine.print;

import com.google.common.base.Optional;
import org.parsky.engine.print.strategy.*;
import org.parsky.engine.print.strategy.character.*;
import org.parsky.grammar.Grammar;
import org.parsky.grammar.rules.*;
import org.parsky.grammar.rules.character.*;

import java.util.HashMap;

public class PrintParserEngine {
    public static String print (Grammar grammar) {
        HashMap<Class, PrintStrategy> map = new HashMap<>();
        HashMap<Class, CharPrintStrategy> charPrint = new HashMap<>();
        AnyPrintStrategy result = new AnyPrintStrategy(map);

        map.put(OneOrMoreRule.class, new OneOrMorePrintStrategy(result));
        map.put(ZeroOrMoreRule.class, new ZeroOrMorePrintStrategy(result));
        map.put(SequenceRule.class, new SequencePrintStrategy(result));
        map.put(ReferenceRule.class, new ReferencePrintStrategy());
        map.put(TransformRule.class, new TransformPrintStrategy(result));
        map.put(FirstOfRule.class, new FirstOfPrintStrategy(result));
        map.put(StringRule.class, new StringPrintStrategy());
        map.put(CaptureTextRule.class, new CaptureTextPrintStrategy(result));
        map.put(CharacterRule.class, new CharacterPrintStrategy(charPrint));
        map.put(FailRule.class, new FailPrintStrategy());
        map.put(EndOfInputRule.class, new EmptyPrintStrategy());
        map.put(TestRule.class, new TestPrintStrategy(result));
        map.put(NotRule.class, new NotPrintStrategy(result));
        map.put(EmptyRule.class, new EmptyPrintStrategy());

        charPrint.put(RangeCharRule.class, new RangeCharPrintStrategy());
        charPrint.put(WhitespaceCharRule.class, new WhitespaceCharPrintStrategy());
        charPrint.put(AnyOfCharRule.class, new AnyOfCharPrintStrategy());
        charPrint.put(NoneOfCharRule.class, new NoneOfCharPrintStrategy());
        charPrint.put(SingleCharRule.class, new SingleCharPrintStrategy());

        StringBuilder stringBuilder = new StringBuilder();
        PrintQueue printQueue = PrintQueue.create();
        Rule start = grammar.rule(grammar.startRule());
        printQueue.enqueue(getStart(start));

        Optional<ReferenceRule> pop = printQueue.pop();
        while (pop.isPresent()) {
            ReferenceRule ruleToPrint = pop.get();
            if (!ruleToPrint.getRule().isPresent()) throw new IllegalStateException(String.format("No expression defined for rule '%s'", ruleToPrint.getName()));
            stringBuilder.append(String.format("%s = %s;", ruleToPrint.getName(), result.print(ruleToPrint.getRule().get(), printQueue).getContent()));
            stringBuilder.append("\n");

            pop = printQueue.pop();
        }

        return stringBuilder.toString();
    }

    private static ReferenceRule getStart(Rule start) {
        if (start instanceof ReferenceRule) return (ReferenceRule) start;
        ReferenceRule referenceRule = new ReferenceRule("start");
        referenceRule.set(start);
        return referenceRule;
    }
}
