package org.jactiverecord.mapping;

import org.jactiverecord.exceptions.AnnotationRequiredException;
import org.jactiverecord.mapping.annotations.Column;
import org.jactiverecord.mapping.annotations.PrimaryKey;
import org.jactiverecord.mapping.annotations.Relationship;
import org.jactiverecord.mapping.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * {@link ObjectMapping} contains the mapping from a ActiveRecord to
 * database information.
 */
public class ObjectMapping {

    /**
     * The {@link Table} annotation
     */
    protected Table table;

    /**
     * The primary key field mapping
     */
    protected FieldMapping primaryKey;

    /**
     * A flag which when true means the record is
     * already persisted else if not persisted is false
     */
    protected boolean persisted = false;

    /**
     * A list of {@link FieldMapping}
     */
    protected final List<FieldMapping> mappings = new ArrayList<>();

    /**
     * Constructs a new {@link ObjectMapping}
     * and maps the object
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
