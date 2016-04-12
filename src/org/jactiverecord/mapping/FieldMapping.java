package org.jactiverecord.mapping;

import org.jactiverecord.mapping.annotations.Column;
import org.jactiverecord.mapping.annotations.PrimaryKey;
import org.jactiverecord.mapping.annotations.Relationship;
import org.jactiverecord.utility.Reflection;

import java.lang.reflect.Field;

/**
 * Created by Francis on 10/04/16.
 * Project Jactive-Record.
 */
public class FieldMapping<T> {

    /**
     * The {@link Field} that is being mapped
     */
    protected final Field field;

    /**
     * The {@link Class} that the field belongs to.
     */
    protected final Class clazz;

    /**
     * The instance to get the value from.
     */
    protected final Object instance;

    /**
     * The initial value of the field to tell
     * if the field value has been changed.
     */
    protected final T initialValue;

    /**
     * A flag {@link PrimaryKey} to set if
     * this {@link FieldMapping} is a primary key.
     */
    protected PrimaryKey primaryKey = null;

    /**
     * The {@link Column} annotation associated
     * with this field.
     */
    protected Column column = null;

    /**
     * The {@link Relationship} of this field
     * if there is one.
     */
    protected Relationship relationship = null;

    /**
     * Constructs a new {@link FieldMapping}.
     * @param clazz the {@link Class} that contains the {@link Field}.
     * @param field the {@link Field} to get the value from.
     * @param instance the instance to get the value from.
     */
    public FieldMapping(final Class clazz, final Field field, final Object instance) {
        this.clazz = clazz;
        this.field = field;
        this.instance = instance;
        this.initialValue = (T) Reflection.getValue(clazz, field, instance);
    }

    /**
     * Gets the value of the field.
     * @return the value of the field.
     */
    public T getValue() {
        return (T) Reflection.getValue(clazz, field, instance);
    }

    /**
     * Checks to see if the field has been
     * modified by comparing it to the initial value.
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
     * Checks to see if the {@link PrimaryKey}
     * variable has been set.
     * @return true if the {@link PrimaryKey}
     * variable has been set else false.
     */
    public boolean isPrimaryKey() {
        return primaryKey != null;
    }
}
