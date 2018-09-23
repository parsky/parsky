package org.parsky.context;

public class DefaultContextFactory implements ContextFactory {
    public Context create() {
        return new Context();
    }
}
