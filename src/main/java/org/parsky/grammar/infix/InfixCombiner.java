package org.parsky.grammar.infix;

import org.parsky.engine.ParserRequest;
import org.parsky.grammar.rules.transform.Transform;

public interface InfixCombiner {
    Transform.Result combine(ParserRequest request, Object leftOperand, Object operator, Object rightOperand);
}
