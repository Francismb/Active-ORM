package org.activeorm.mapping;

import org.activeorm.exceptions.AnnotationRequiredException;
import org.activeorm.exceptions.DuplicatePrimaryKeyException;
import org.activeorm.ActiveRecord;
import org.activeorm.mapping.annotations.Column;
import org.activeorm.mapping.annotations.PrimaryKey;
import org.activeorm.mapping.annotations.Table;
import org.activeorm.mapping.relationships.Relationship;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 * <p>
 * Represents the mapping of a {@link ActiveRecord}
 */
public class ObjectMapping {

    /**
     * The {@link Table} annotation.
     */
    public Table table;

    /**
     * The primary key field mapping.
     */
    public AttributeMapping primaryKey;

    /**
     * A flag which when true means the record is
     * already persisted else if not persisted is false.
     */
    public boolean persisted;

    /**
     * A list of {@link AttributeMapping}.
     */
    public final List<AttributeMapping> attributes = new ArrayList<>();

    /**
     * Constructs a new {@link ObjectMapping} and maps the object.
     *
     * @param clazz    the class to map.
     * @param instance the instance of the class to map.
     */
    public ObjectMapping(final Class clazz, final Object instance) {
        // Set the table annotation
        if (clazz.isAnnotationPresent(Table.class)) {
            this.table = (Table) clazz.getAnnotation(Table.class);
        } else {
            throw new AnnotationRequiredException("Table annotation is required on ActiveRecord");
        }
        for (final Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                if (field.getClass().isAssignableFrom(Relationship.class)) {

                } else {
                    // Create a mapping
                    final AttributeMapping attribute = new AttributeMapping(field, clazz, instance);

                    // Set the mappings column annotations
                    attribute.column = field.getAnnotation(Column.class);

                    // Set the primary key annotation if it is present
                    if (field.isAnnotationPresent(PrimaryKey.class)) {

                        // If there is already one primary key specified throw a exception
                        if (primaryKey != null) {
                            throw new DuplicatePrimaryKeyException("Duplicate primary keys found " + getClass().getName() + "." + primaryKey.field.field.getName() + " and " + getClass().getName() + "." + field.getName());
                        }

                        // Set the mappings primary key value to the primary key annotation
                        attribute.primaryKey = field.getAnnotation(PrimaryKey.class);

                        // Set the primary key field
                        primaryKey = attribute;
                    }

                    // Add the mapping
                    attributes.add(attribute);
                }
            }
        }
    }
}
