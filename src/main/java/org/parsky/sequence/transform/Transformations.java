package org.parsky.sequence.transform;

import com.google.common.base.Function;

public class Transformations {
    public static <T> Transformation<T> fromString(Function<ContentTransformation.Request<String>, T> function) {
        return from(function);
    }
    public static <T> Transformation<T> fromString(String label, Function<ContentTransformation.Request<String>, T> function) {
        return new LabelledTransformation<>(label, fromString(function));
    }

    public static <T> Transformation<T> fromContentList(Function<ListContentTransformation.Request, T> function) {
        return new ListContentTransformation<>(function);
    }

    public static <T> Transformation<T> fromContentList(String label, Function<ListContentTransformation.Request, T> function) {
        return new LabelledTransformation<>(label, fromContentList(function));
    }

    public static <T> Transformation<T> constant(final T value) {
        return new ConstantTransformation<>(value);
    }

    public static <T> Transformation<T> constant(String label, T value) {
        return new LabelledTransformation<>(label, constant(value));
    }

    public static <I, T> Transformation<T> from(Function<ContentTransformation.Request<I>, T> function) {
        return new ContentTransformation<>(function);
    }


    public static <I, T> Transformation<T> from(String label, Function<ContentTransformation.Request<I>, T> function) {
        return new LabelledTransformation<>(label, from(function));
    }

    public static <T> Transformation<T> identity () {
        return new IdentityTransformation<>();
    }

    public static <T> Transformation<T> identity (String label) {
        return new LabelledTransformation<>(label, Transformations.<T>identity());
    }

    public static <T> Transformation<T> as (final Class<T> type) {
        return new CastTransformation<>(type);
    }

    public static <T> Transformation<T> as (String label, Class<T> type) {
        return new LabelledTransformation<>(label, as(type));
    }
}
