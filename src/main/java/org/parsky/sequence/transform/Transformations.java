package org.parsky.sequence.transform;

import com.google.common.base.Function;

public class Transformations {
    public static <T> Transformation<T> fromString(Function<ContentTransformation.Request<String>, T> function) {
        return new ContentTransformation<>(function);
    }

    public static <T> Transformation<T> fromContentList(Function<ListContentTransformation.Request, T> function) {
        return new ListContentTransformation<>(function);
    }

    public static <T> Transformation<T> constant(final T value) {
        return new ConstantTransformation<>(value);
    }

    public static <I, T> Transformation<T> from (Function<ContentTransformation.Request<I>, T> function) {
        return new ContentTransformation<>(function);
    }

    public static <T> Transformation<T> identity () {
        return new IdentityTransformation<>();
    }

    public static <T> Transformation<T> as (final Class<T> type) {
        return new CastTransformation<>(type);
    }
}
