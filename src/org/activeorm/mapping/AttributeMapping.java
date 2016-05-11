package org.activeorm.mapping;

import org.activeorm.mapping.annotations.Column;
import org.activeorm.mapping.annotations.PrimaryKey;
import org.activeorm.mapping.relationships.Relationship;
import org.activeorm.utility.ReflectedField;

import java.lang.reflect.Field;

/**
 * Created by Francis on 5/11/2016.
 * Project Active-ORM.
 *
 * Represents the mapping of a active record attribute.
 */
public class AttributeMapping<T> {

    /**
     * The {@link Column} annotation associated
     * with this field.
     */
    public Column column;

    /**
     * The {@link Class} that the field belongs to.
     */
    public PrimaryKey primaryKey;

    /**
     * The {@link ReflectedField} that this {@link AttributeMapping} uses.
     */
    public final ReflectedField<T> field;

    /**
     * Constructs a new {@link AttributeMapping}.
     *
     * @param field the {@link Field} to get the value from.
     * @param instance the instance to get the value from.
     */
    public AttributeMapping(final Field field, final Object instance) {
        this.field = new ReflectedField<T>(field, instance);
    }

    /**
     * Checks to see if the {@link PrimaryKey}
     * variable has been set.
     *
     * @return true if the {@link PrimaryKey}
     * variable has been set else false.
     */
    public boolean isPrimaryKey() {
        return primaryKey != null;
    }
}
