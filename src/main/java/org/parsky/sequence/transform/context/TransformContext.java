package org.parsky.sequence.transform.context;

import org.parsky.context.Context;
import org.parsky.sequence.model.Range;

public class TransformContext {
    private final Range range;
    private final boolean testMode;
    private final Context context;

    public TransformContext(Range range, boolean testMode, Context context) {
        this.range = range;
        this.testMode = testMode;
        this.context = context;
    }

    public Range getRange() {
        return range;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public Context getContext() {
        return context;
    }
}
