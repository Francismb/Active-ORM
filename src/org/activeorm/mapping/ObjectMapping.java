package org.activeorm.mapping;

import org.activeorm.exceptions.AnnotationRequiredException;
import org.activeorm.exceptions.DuplicatePrimaryKeyException;
import org.activeorm.mapping.annotations.Column;
import org.activeorm.mapping.annotations.PrimaryKey;
import org.activeorm.mapping.annotations.Relationship;
import org.activeorm.mapping.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * Represents the mapping of a {@link ActiveRecord}
 */
public class ObjectMapping {

    /**
     * The {@link Table} annotation.
     */
    protected Table table;

    /**
     * The primary key field mapping.
     */
    protected FieldMapping primaryKey;

    /**
     * A flag which when true means the record is
     * already persisted else if not persisted is false.
     */
    protected boolean persisted;

    /**
     * A list of {@link FieldMapping}.
     */
    public final List<FieldMapping> mappings = new ArrayList<>();

    /**
     * Constructs a new {@link ObjectMapping} and maps the object.
     * @param clazz the class to map.
     * @param instance the instance of the class to map.
     */
    protected ObjectMapping(final Class clazz, final Object instance) {
        // Set the table annotation
        if (clazz.isAnnotationPresent(Table.class)) {
            this.table = (Table) clazz.getAnnotation(Table.class);
        } else {
            throw new AnnotationRequiredException("Table annotation is required on ActiveRecord");
        }
        for (final Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                // Create a mapping
                final FieldMapping mapping = new FieldMapping(clazz, field, instance);

                // Set the mappings column annotations
                mapping.column = field.getAnnotation(Column.class);

                // Set the primary key annotation if it is present
                if (field.isAnnotationPresent(PrimaryKey.class)) {

                    // If there is already one primary key specified throw a exception
                    if (primaryKey != null) {
                        throw new DuplicatePrimaryKeyException("Duplicate primary keys found " + getClass().getName() + "." + primaryKey.field.getName() + " and " + getClass().getName() + "." + field.getName());
                    }

                    // Set the mappings primary key value to the primary key annotation
                    mapping.primaryKey = field.getAnnotation(PrimaryKey.class);

                    // Set the primary key field
                    primaryKey = mapping;
                }

                // Set the relationship annotation if it is present
                if (field.isAnnotationPresent(Relationship.class)) {
                    mapping.relationship = field.getAnnotation(Relationship.class);
                }

                // Add the mapping
                mappings.add(mapping);
            }
        }
    }
}
