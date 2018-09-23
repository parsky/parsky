package org.parsky.grammar;

import java.util.*;

public class DependencyManager<Item> {
    public static <Item> DependencyManager<Item> create (Map<Item, Set<Item>> dependencies) {
        Map<Item, Set<Item>> dependsOn = new HashMap<>(dependencies);
        Map<Item, Set<Item>> isDependencyOf = new HashMap<>();

        for (Map.Entry<Item, Set<Item>> entry : dependencies.entrySet()) {
            for (Item item : entry.getValue()) {
                if (!isDependencyOf.containsKey(item))
                    isDependencyOf.put(item, new HashSet<Item>());

                isDependencyOf.get(item).add(entry.getKey());
            }
        }

        for (Item dependencyOf : isDependencyOf.keySet()) {
            if (!dependsOn.containsKey(dependencyOf)) {
                throw new IllegalStateException(String.format(
                        "%s which is a dependency for %s is not provided",
                        dependencyOf,
                        isDependencyOf.get(dependencyOf)
                ));
            }
        }

        return new DependencyManager<>(dependsOn, isDependencyOf);
    }


    private final Map<Item, Set<Item>> dependsOn;
    private final Map<Item, Set<Item>> isDependencyOf;

    private DependencyManager(Map<Item, Set<Item>> dependsOn, Map<Item, Set<Item>> isDependencyOf) {
        this.dependsOn = dependsOn;
        this.isDependencyOf = isDependencyOf;
    }

    public boolean isEmpty () {
        return dependsOn.isEmpty();
    }

    public Item nextFreeFactory() {
        for (Map.Entry<Item, Set<Item>> entry : dependsOn.entrySet()) {
            if (entry.getValue().isEmpty()) {
                dependsOn.remove(entry.getKey());
                for (Item item : getDependentsOn(entry.getKey())) {
                    dependsOn.get(item).remove(entry.getKey());
                }

                return entry.getKey();
            }
        }

        throw new IllegalStateException(String.format(
                "Circular dependency identified between %s",
                dependsOn.keySet()
        ));
    }

    private Set<Item> getDependentsOn(Item key) {
        if (isDependencyOf.containsKey(key)) {
            return isDependencyOf.get(key);
        } else {
            return Collections.emptySet();
        }
    }
}
