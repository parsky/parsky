package org.parsky;

import org.parsky.context.Context;
import org.parsky.engine.ParserRequest;

public class TestUtils {
    public static ParserRequest request (String content) {
        return new ParserRequest(content.toCharArray(), 0, false, new Context());
    }
}
