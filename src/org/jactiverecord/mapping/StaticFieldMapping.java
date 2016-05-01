package org.jactiverecord.mapping;

import org.jactiverecord.mapping.annotations.Column;
import org.jactiverecord.mapping.annotations.PrimaryKey;

import java.lang.reflect.Field;

/**
 * Created by Francis on 30/04/16.
 * Project Jactive-Record.
 *
 * Represents the static mapping of a field.
 */
public class StaticFieldMapping {

    /**
     * The {@link Field} that is being mapped.
     */
    public final Field field;

    /**
     * The {@link Class} that the field belongs to.
     */
    public final Class clazz;

    /**
     * A flag {@link PrimaryKey} to set if
     * this {@link FieldMapping} is a primary key.
     */
    public PrimaryKey primaryKey = null;

    /**
     * The {@link Column} annotation associated
     * with this field.
     */
    public Column column = null;

    /**
     * Constructs a new {@link StaticFieldMapping}.
     *
     * @param clazz the {@link Class} that contains the {@link Field}.
     * @param field the {@link Field} that represents the column
     */
    public StaticFieldMapping(final Class clazz, final Field field) {
        this.clazz = clazz;
        this.field = field;
    }
}
