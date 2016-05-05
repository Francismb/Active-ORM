package org.activeorm.utility;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Created by Francis on 10/04/16.
 * Project Jactive-Record.
 */
public class Reflection {

    public static Object getValue(final Class clazz, final String fieldName, final Object instance) {
        return getValue(clazz, getField(clazz, fieldName), instance);
    }

    public static Object[] getArray(final Class clazz, final String fieldName, final Object instance) {
        return getArray(clazz, getField(clazz, fieldName), instance);
    }

    public static Object getValue(final Class clazz, final Field field, final Object instance) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field.get(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setValue(final Class clazz, final String fieldName, final Object instance, final Object value) {
        setValue(clazz, getField(clazz, fieldName), instance, value);
    }

    public static void setValue(final Class clazz, final Field field, final Object instance, final Object value) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object[] getArray(final Class clazz, final Field field, final Object instance) {
        final Object object = getValue(clazz, field, instance);
        final Class type = object.getClass();
        final int length = Array.getLength(object);
        final Object[] array = (Object[]) Array.newInstance(type, length);
        for (int i = 0; i < length; i++) {
            array[i] = Array.get(object, i);
        }
        return array;
    }

    public static Field getField(final Class clazz, final String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            try {
                field = clazz.getField(fieldName);
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            }
        }
        return field;
    }

}