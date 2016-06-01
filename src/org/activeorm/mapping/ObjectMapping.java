package org.activeorm.mapping;

import org.activeorm.exceptions.AnnotationRequiredException;
import org.activeorm.exceptions.DuplicatePrimaryKeyException;
import org.activeorm.ActiveRecord;
import org.activeorm.mapping.annotations.Column;
import org.activeorm.mapping.annotations.PrimaryKey;
import org.activeorm.mapping.annotations.Table;
import org.activeorm.mapping.relationships.BelongsTo;
import org.activeorm.mapping.relationships.HasMany;
import org.activeorm.mapping.relationships.HasOne;
import org.activeorm.mapping.relationships.Relationship;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 9/04/16.
 * Project Active-ORM.
 *
 * Represents the mapping of a {@link ActiveRecord}
 */
public class ObjectMapping {

    /**
     * The {@link Table} annotation.
     */
    public Table table;

    /**
     * The primary key attributes.
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
     * A list of {@link Relationship}
     */
    public final List<Relationship> relationships = new ArrayList<>();

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
                if (Relationship.class.isAssignableFrom(field.getType())) {
                    // Create the attribute
                    final AttributeMapping attribute = new AttributeMapping(field, instance);

                    // Set the attributes column annotation
                    attribute.column = field.getAnnotation(Column.class);

                    // If the column relationship contains no data throw an exception
                    if (attribute.column.name().equals("null")) {
                        throw new IllegalArgumentException("Column annotation requires a relationship column name");
                    }

                    // Add the attribute
                    attributes.add(attribute);

                    // Get the field type
                    final Class<?> type = field.getType();

                    // Create the relationship instance and bind it to the field.
                    if (type.equals(BelongsTo.class)) {
                        final BelongsTo belongsTo = new BelongsTo(attribute);
                        belongsTo.bind();
                        relationships.add(belongsTo);
                    } else if (type.equals(HasOne.class)) {
                        final HasOne hasOne = new HasOne(attribute);
                        hasOne.bind();
                        relationships.add(hasOne);
                    } else if (type.equals(HasMany.class)) {
                        final HasMany hasMany = new HasMany(attribute);
                        hasMany.bind();
                        relationships.add(hasMany);
                    }
                } else {
                    // Create the attribute
                    final AttributeMapping attribute = new AttributeMapping(field, instance);

                    // Set the attributes column annotations
                    attribute.column = field.getAnnotation(Column.class);

                    // If the column name contains no data throw an exception
                    if (attribute.column.name().equals("null")) {
                        throw new IllegalArgumentException("Column annotation requires a column name");
                    }

                    // Set the primary key annotation if it is present
                    if (field.isAnnotationPresent(PrimaryKey.class)) {

                        // If there is already one primary key specified throw a exception
                        if (primaryKey != null) {
                            throw new DuplicatePrimaryKeyException("Duplicate primary keys found " + getClass().getName() + "." + primaryKey.field.field.getName() + " and " + getClass().getName() + "." + field.getName());
                        }

                        // Set the attributes primary key value to the primary key annotation
                        attribute.primaryKey = field.getAnnotation(PrimaryKey.class);

                        // Set the primary key field
                        primaryKey = attribute;
                    }

                    // Add the attributes
                    attributes.add(attribute);
                }
            }
        }
    }

    /**
     * Resets the attributes so that there initial value
     * is equal to the current value.
     */
    public void resetAttributes() {
        for (final AttributeMapping attribute : attributes) {
            attribute.field.reset();
        }
    }
}
