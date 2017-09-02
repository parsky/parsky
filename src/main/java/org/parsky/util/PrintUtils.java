package org.parsky.util;

import org.parsky.sequence.model.tree.ContentNode;

import java.lang.reflect.Method;

public class PrintUtils {
    public static String typeAndParameters (Object instance) {
        if (instance == null) return "null";

        if (ContentNode.class.isAssignableFrom(instance.getClass())) {
            Method method = null;
            try {
                method = ContentNode.class.getMethod("getContent");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return String.format("%s<%s>", instance.getClass().getName(), ((Class) method.getGenericReturnType()).getName());
        }
        return instance.getClass().getName();
    }
}
