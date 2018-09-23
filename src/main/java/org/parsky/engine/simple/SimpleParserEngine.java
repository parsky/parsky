package org.parsky.engine.simple;

import org.parsky.engine.ParserEngine;
import org.parsky.engine.simple.strategy.*;
import org.parsky.engine.simple.strategy.character.*;
import org.parsky.grammar.rules.*;
import org.parsky.grammar.rules.character.*;

import java.util.HashMap;

public class SimpleParserEngine {
    public static ParserEngine create () {
        HashMap<Class, ParserEngine> map = new HashMap<>();
        HashMap<Class, CharRuleEngine> charRule = new HashMap<>();
        charRule.put(RangeCharRule.class, new RangeCharRuleEngine());
        charRule.put(WhitespaceCharRule.class, new WhitespaceCharRuleEngine());
        charRule.put(AnyOfCharRule.class, new AnyOfCharRuleEngine());
        charRule.put(NoneOfCharRule.class, new NoneOfCharRuleEngine());
        charRule.put(SingleCharRule.class, new SingleCharRuleEngine());

        ParserEngine result = new AnyParserEngine(map);
        map.put(CaptureTextRule.class, new CaptureTextParserEngine(result));
        map.put(CharacterRule.class, new CharacterParserEngine(charRule));
        map.put(EmptyRule.class, new EmptyParserEngine());
        map.put(EndOfInputRule.class, new EndOfInputParserEngine());
        map.put(FailRule.class, new FailParserEngine());
        map.put(FirstOfRule.class, new FirstOfParserEngine(result));
        map.put(NotRule.class, new NotParserEngine(result));
        map.put(OneOrMoreRule.class, new OneOrMoreParserEngine(result));
        map.put(ReferenceRule.class, new ReferenceParserEngine(result));
        map.put(SequenceRule.class, new SequenceParserEngine(result));
        map.put(StringRule.class, new StringParserEngine());
        map.put(TestRule.class, new TestParserEngine(result));
        map.put(TransformRule.class, new TransformParserEngine(result));
        map.put(ZeroOrMoreRule.class, new ZeroOrMoreParserEngine(result));
        return result;
    }
}
