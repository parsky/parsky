package org.parsky.sequence.transform;

import com.google.common.base.Function;

public class Transformations {
    public static Transformation fromString(Function<ContentTransformation.Request<String>, Transformation.Result> function) {
        return from(function);
    }
    public static Transformation fromString(String label, Function<ContentTransformation.Request<String>, Transformation.Result> function) {
        return new LabelledTransformation(label, fromString(function));
    }

    public static Transformation fromContentList(Function<ListContentTransformation.Request, Transformation.Result> function) {
        return new ListContentTransformation(function);
    }

    public static Transformation fromContentList(String label, Function<ListContentTransformation.Request, Transformation.Result> function) {
        return new LabelledTransformation(label, fromContentList(function));
    }

    public static <T> Transformation constant(final T value) {
        return new ConstantTransformation(value);
    }

    public static <T> Transformation constant(String label, T value) {
        return new LabelledTransformation(label, Transformations.<T>constant(value));
    }

    public static <I> Transformation from(Function<ContentTransformation.Request<I>, Transformation.Result> function) {
        return new ContentTransformation<>(function);
    }


    public static <I> Transformation from(String label, Function<ContentTransformation.Request<I>, Transformation.Result> function) {
        return new LabelledTransformation(label, from(function));
    }

    public static Transformation pick (final int index) {
        return fromContentList(new Function<ListContentTransformation.Request, Transformation.Result>() {
            @Override
            public Transformation.Result apply(ListContentTransformation.Request input) {
                return Transformation.Result.success(input.get(index));
            }
        });
    }
}
