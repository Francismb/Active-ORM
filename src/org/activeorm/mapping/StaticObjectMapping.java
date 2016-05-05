package org.activeorm.mapping;

import org.activeorm.exceptions.AnnotationRequiredException;
import org.activeorm.mapping.annotations.Column;
import org.activeorm.mapping.annotations.PrimaryKey;
import org.activeorm.mapping.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 30/04/16.
 * Project Jactive-Record.
 *
 * Represents the static mapping of a {@link ActiveRecord}
 */
public class StaticObjectMapping {

    /**
     * The {@link Table} annotation.
     */
    public Table table;

    /**
     * A list of {@link FieldMapping}.
     */
    public final List<StaticFieldMapping> mappings = new ArrayList<>();

    /**
     * Constructs a new {@link StaticObjectMapping} and maps the object.
     * @param clazz the class to map.
     */
    public StaticObjectMapping(final Class clazz) {
        // Set the table annotation
        if (clazz.isAnnotationPresent(Table.class)) {
            this.table = (Table) clazz.getAnnotation(Table.class);
        } else {
            throw new AnnotationRequiredException("Table annotation is required on ActiveRecord");
        }
        for (final Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                // Create a mapping
                final StaticFieldMapping mapping = new StaticFieldMapping(clazz, field);

                // Set the mappings column annotations
                mapping.column = field.getAnnotation(Column.class);

                // Set the primary key annotation if it is present
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    mapping.primaryKey = field.getAnnotation(PrimaryKey.class);
                }

                // Add the mapping
                mappings.add(mapping);
            }
        }
    }
}
