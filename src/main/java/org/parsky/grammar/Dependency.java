package org.parsky.grammar;

public class Dependency {
    public static String nameOf(Object item) {
        if (item instanceof Class) return ((Class) item).getName();
        else if (item instanceof String) return (String) item;
        else throw new IllegalArgumentException(String.format("Invalid reference for dependency '%s'", item));
    }

    public static Dependency[] children(Object... list) {
        Dependency[] dependencies = new Dependency[list.length];
        for (int i = 0; i < list.length; i++) {
            dependencies[i] = child(nameOf(list[i]));
        }
        return dependencies;
    }

    public static Dependency child(String name) {
        return new Dependency(nameOf(name), false);
    }

    public static Dependency child(Class name) {
        return new Dependency(nameOf(name), false);
    }

    public static Dependency recursive(String name) {
        return new Dependency(name, true);
    }
    public static Dependency recursive(Class name) {
        return new Dependency(nameOf(name), true);
    }

    private final String name;
    private final boolean parent;

    public Dependency(String name, boolean parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public boolean isParent() {
        return parent;
    }
}
