package org.parsky.sequence.transform;

import com.google.common.base.Function;

public class Transformations {
    public static <C, T> Transformation<C> fromString(Function<ContentTransformation.Request<C, String>, T> function) {
        return from(function);
    }
    public static <C, T> Transformation<C> fromString(String label, Function<ContentTransformation.Request<C, String>, T> function) {
        return new LabelledTransformation<>(label, fromString(function));
    }

    public static <C, I, T> Transformation<C> fromContentList(Function<ListContentTransformation.Request<C, I>, T> function) {
        return new ListContentTransformation<>(function);
    }

    public static <C, I, T> Transformation<C> fromContentList(String label, Function<ListContentTransformation.Request<C, I>, T> function) {
        return new LabelledTransformation<>(label, fromContentList(function));
    }

    public static <C, V, T> Transformation<C> constant(final T value) {
        return new ConstantTransformation<>(value);
    }

    public static <C, T> Transformation<C> constant(String label, T value) {
        return new LabelledTransformation<>(label, Transformations.<C, Object, T>constant(value));
    }

    public static <C, I, T> Transformation<C> from(Function<ContentTransformation.Request<C, I>, T> function) {
        return new ContentTransformation<>(function);
    }


    public static <C, I, T> Transformation<C> from(String label, Function<ContentTransformation.Request<C,  I>, T> function) {
        return new LabelledTransformation<>(label, from(function));
    }

    public static <C, T> Transformation<C> identity () {
        return new IdentityTransformation<>();
    }

    public static <C, T> Transformation<C> identity (String label) {
        return new LabelledTransformation<>(label, Transformations.<C, T>identity());
    }

    public static <C, T> Transformation<C> as (final Class<T> type) {
        return new CastTransformation<>(type);
    }

    public static <C, T> Transformation<C> as (String label, Class<T> type) {
        return new LabelledTransformation<>(label, Transformations.<C, T>as(type));
    }
}
