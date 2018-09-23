package org.parsky.grammar.rules.transform;

import org.parsky.ParskyException;
import org.parsky.engine.ParserRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Transforms {
    public static Transform pick (final int position) {
        return list(new ListTransform.TransformList() {
            @Override
            public Transform.Result transform(ParserRequest request, ListTransform.Request input) {
                if (input.size() > position) {
                    return Transform.Result.success(input.get(position));
                } else {
                    throw new IllegalArgumentException(String.format("Expecting to pick position %d of list, but list only has %d elements", position, input.size()));
                }
            }
        });
    }
    public static Transform select (final int... positions) {
        return list(new ListTransform.TransformList() {
            @Override
            public Transform.Result transform(ParserRequest request, ListTransform.Request input) {
                List result = new ArrayList();
                for (int position : positions) {
                    if (input.size() > position) {
                        result.add(input.get(position));
                    } else {
                        throw new IllegalArgumentException(String.format("Expecting to pick position %d of list, but list only has %d elements", position, input.size()));
                    }
                }
                return Transform.Result.success(result);
            }
        });
    }

    public static Transform flatten () {
        return list(new ListTransform.TransformList() {
            @Override
            public Transform.Result transform(ParserRequest request, ListTransform.Request input) {
                List result = new ArrayList();
                for (Object item : input) {
                    if (item instanceof Collection) {
                        result.addAll((Collection) item);
                    } else {
                        result.add(item);
                    }
                }
                return Transform.Result.success(result);
            }
        });
    }

    public static Transform value(final Object value) {
        return new Transform() {
            @Override
            public Result transform(ParserRequest request, Object input) {
                return Result.success(value);
            }
        };
    }

    public static Transform list (ListTransform.TransformList transform) {
        return new ListTransform(transform);
    }
}
