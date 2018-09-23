package org.parsky.position;

import org.apache.commons.lang3.StringUtils;
import org.parsky.context.Label;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PrintLabelListService {
    protected String printTree(Collection<Label> tree) {
        int count = 1;
        List<String> labels = new ArrayList<>();
        Iterator<Label> iterator = tree.iterator();

        while (iterator.hasNext()) {
            Label next = iterator.next();
            labels.add(String.format(" %d. %s%n", count++, next.getLabel()));
        }

        if (labels.isEmpty()) return "";
        return "Grammar Rules:\n"+StringUtils.join(labels, "");
    }
}
