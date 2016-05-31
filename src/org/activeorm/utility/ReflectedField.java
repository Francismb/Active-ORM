package org.activeorm.utility;

import java.lang.reflect.Field;

/**
 * Created by Francis on 5/11/2016.
 * Project Active-ORM.
 *
 * This class provides the ability to get and set the value of a field
 * and also determine if the fields value has been changed.
 */
public class ReflectedField<T> {

    /**
     * The {@link Field} that is being reflected.
     */
    public final Field field;

    /**
     * The instance to get the value from.
     */
    private final Object instance;

    /**
     * The initial value of the field to tell
     * if the field value has been changed.
     */
    private T initialValue;

    /**
     * Constructs a new {@link ReflectedField}.
     *
     * @param field the {@link Field} to get the value from.
     * @param instance the instance to get the value from.
     */
    public ReflectedField(final Field field, final Object instance) {
        this.field = field;
        this.instance = instance;
        this.initialValue = getValue();
    }

    /**
     * Gets the value of the field.
     *
     * @return the value of the field.
     */
    public T getValue() {
        return (T) Reflection.getValue(field, instance);
    }

    /**
     * Sets the value of the field.
     *
     * @param value the value for the field to be set to.
     */
    public void setValue(final T value) {
        Reflection.setValue(field, instance, value);
    }

    /**
     * Checks to see if the field has been
     * modified by comparing it to the initial value.
     *
     * @return true if the field has been modified else false.
     */
    public boolean hasBeenModified() {
        final T value = getValue();
        if (initialValue == null) {
            return value != null;
        }
        return !initialValue.equals(getValue());
    }

    /**
     * Resets the initial value to the current one.
     */
    public void reset() {
        this.initialValue = getValue();
    }

    /**
     * Gets the name of the field.
     *
     * @return the name of the field.
     */
    public String getName() {
        return field.getName();
    }

    /**
     * Gets the type of the field.
     *
     * @return the type of the field.
     */
    public Class getType() {
        return field.getType();
    }
}
