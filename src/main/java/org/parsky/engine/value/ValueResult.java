package org.parsky.engine.value;

public interface ValueResult {
    Object get ();
    ValueResult append(ValueResult value);
}
