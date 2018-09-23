package org.parsky.engine;

import com.google.common.base.Optional;
import org.parsky.engine.value.ConstantValueResult;
import org.parsky.engine.value.ValueResult;
import org.parsky.grammar.rules.transform.Transform;

public class ParserResult {
    private final int step;
    private final Optional<ValueResult> value;
    private final Optional<String> error;

    public ParserResult(int step, Optional<ValueResult> value, Optional<String> error) {
        this.step = step;
        this.value = value;
        this.error = error;
    }

    public boolean success() {
        return value.isPresent() && !getError().isPresent();
    }

    public boolean hasProgress() {
        return value.isPresent() && step > 0;
    }

    public int getSteps() {
        return step;
    }

    public Object getValue () {
        return value.get().get();
    }

    public Optional<String> getError() {
        return error;
    }

    public ParserResult plus(ParserResult child) {
        int step = this.step + child.step;
        if (success() && child.success()) {
            return new ParserResult(step, Optional.of(
                    value.get().append(child.value.get())
            ), Optional.<String>absent());
        } else {
            if (child.getError().isPresent()) {
                return new ParserResult(step, Optional.<ValueResult>absent(), child.error);
            } else if (!child.value.isPresent()) {
                return new ParserResult(step, Optional.<ValueResult>absent(), Optional.<String>absent());
            } else {
                return this;
            }
        }
    }

    public ParserResult transform(ParserRequest request, Transform transform) {
        if (value.isPresent()) {
            Transform.Result result = calculate(request, transform);

            if (result.isSuccess()) {
                return new ParserResult(
                        step,
                        Optional.<ValueResult>of(ConstantValueResult.constant(result.getValue())),
                        Optional.<String>absent()
                );
            } else {
                return new ParserResult(
                        step,
                        Optional.<ValueResult>absent(),
                        Optional.of(result.getError())
                );
            }
        } else {
            return this;
        }
    }

    private Transform.Result calculate(ParserRequest request, Transform transform) {
        return transform.transform(request, value.get().get());
    }

    public ParserResult step(int step) {
        return new ParserResult(
                step,
                value,
                error
        );
    }

    public ParserResult withValue(ValueResult value) {
        return new ParserResult(
                step,
                Optional.of(value),
                error
        );
    }
}
