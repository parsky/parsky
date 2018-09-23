package org.parsky.engine.print;

import com.google.common.base.Optional;
import org.parsky.grammar.rules.ReferenceRule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrintQueue {
    public static PrintQueue create () {
        return new PrintQueue(new ArrayList<String>(), new LinkedList<ReferenceRule>());
    }

    private final List<String> historic;
    private final Queue<ReferenceRule> queue;

    private PrintQueue(List<String> historic, Queue<ReferenceRule> queue) {
        this.historic = historic;
        this.queue = queue;
    }

    public void enqueue (ReferenceRule rule) {
        this.queue.add(rule);
    }

    public Optional<ReferenceRule> pop () {
        while (!queue.isEmpty()) {
            ReferenceRule rule = queue.poll();

            if (!historic.contains(rule.getName())) {
                this.historic.add(rule.getName());
                return Optional.of(rule);
            }
        }

        return Optional.absent();
    }
}
