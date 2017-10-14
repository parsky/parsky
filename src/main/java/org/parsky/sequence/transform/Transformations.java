package org.parsky.sequence.transform;

import com.google.common.base.Function;

import java.util.List;

public class Transformations {
    public static <C, T> Transformation<C, String, T> fromString(Function<ContentTransformation.Request<C, String>, T> function) {
        return from(function);
    }
    public static <C, T> Transformation<C, String, T> fromString(String label, Function<ContentTransformation.Request<C, String>, T> function) {
        return new LabelledTransformation<>(label, fromString(function));
    }

    public static <C, I, T> Transformation<C, List<I>, T> fromContentList(Function<ListContentTransformation.Request<C, I>, T> function) {
        return new ListContentTransformation<>(function);
    }

    public static <C, I, T> Transformation<C, List<I>, T> fromContentList(String label, Function<ListContentTransformation.Request<C, I>, T> function) {
        return new LabelledTransformation<>(label, fromContentList(function));
    }

    public static <C, V, T> Transformation<C, V, T> constant(final T value) {
        return new ConstantTransformation<>(value);
    }

    public static <C, T> Transformation<C, ?, T> constant(String label, T value) {
        return new LabelledTransformation<>(label, Transformations.<C, Object, T>constant(value));
    }

    public static <C, I, T> Transformation<C, I, T> from(Function<ContentTransformation.Request<C, I>, T> function) {
        return new ContentTransformation<>(function);
    }


    public static <C, I, T> Transformation<C, I, T> from(String label, Function<ContentTransformation.Request<C,  I>, T> function) {
        return new LabelledTransformation<>(label, from(function));
    }

    public static <C, T> Transformation<C, T, T> identity () {
        return new IdentityTransformation<>();
    }

    public static <C, T> Transformation<C, T, T> identity (String label) {
        return new LabelledTransformation<>(label, Transformations.<C, T>identity());
    }

    public static <C, T> Transformation<C, ?, T> as (final Class<T> type) {
        return new CastTransformation<>(type);
    }

    public static <C, T> Transformation<C, ?, T> as (String label, Class<T> type) {
        return new LabelledTransformation<>(label, Transformations.<C, T>as(type));
    }
}
