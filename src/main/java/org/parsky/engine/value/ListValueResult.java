package org.parsky.engine.value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListValueResult implements ValueResult {
    public static ListValueResult list() {
        return new ListValueResult(Collections.emptyList());
    }

    private final List list;

    private ListValueResult(List list) {
        this.list = list;
    }

    @Override
    public Object get() {
        return list;
    }

    @Override
    public ValueResult append(ValueResult value) {
        ArrayList result = new ArrayList();
        for (Object item : list) {
            result.add(item);
        }
        result.add(value.get());
        return new ListValueResult(result);
    }
}
