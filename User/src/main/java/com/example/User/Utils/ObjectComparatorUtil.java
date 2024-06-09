package com.example.User.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectComparatorUtil {

    public static <T> String compareObject(T obj1, T obj2) throws IllegalAccessException {
        if (!obj1.getClass().equals(obj2.getClass())) {
            throw new IllegalArgumentException("Wrong parameters");
        }
        Field[] fields = obj1.getClass().getDeclaredFields();

        StringBuilder changes = new StringBuilder();

        List<String> changedFields = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            Object val1 = field.get(obj1);
            Object val2 = field.get(obj2);

            if (isPrimitiveOrWrapperOrString(field.getType())) {
                if ((val1 != null && !val1.equals(val2)) || (val1 == null && val2 != null)) {
                    changedFields.add(String.format("%s changed %s -> %s", field.getName(), val1, val2));
                }
            }
        }

        if (changedFields.isEmpty()) {
            return null;
        } else {
            for (String change : changedFields) {
                changes.append(change).append("\n");
            }
        }

        return changes.toString();
    }

    private static boolean isPrimitiveOrWrapperOrString(Class<?> type) {
        return type.isPrimitive() ||
                type.equals(Boolean.class) ||
                type.equals(Integer.class) ||
                type.equals(Character.class) ||
                type.equals(Byte.class) ||
                type.equals(Short.class) ||
                type.equals(Double.class) ||
                type.equals(Long.class) ||
                type.equals(Float.class) ||
                type.equals(String.class);
    }
}
