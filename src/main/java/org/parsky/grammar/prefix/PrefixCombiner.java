package org.parsky.grammar.prefix;

import org.parsky.engine.ParserRequest;
import org.parsky.grammar.rules.transform.Transform;

public interface PrefixCombiner {
    Transform.Result combine(ParserRequest request, Object operator, Object element);
}
