package org.jactiverecord.mapping;

import org.jactiverecord.mapping.annotations.Column;
import org.jactiverecord.mapping.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * {@link ObjectMapping} contains the mapping from a ActiveRecord to
 * database information
 */
public class ObjectMapping {

    /**
     * The {@link Table} annotation
     */
    protected Table table;

    /**
     * A list of {@link FieldMapping}
     */
    protected final List<FieldMapping> mappings = new ArrayList<FieldMapping>();

    /**
     * Constructs a new {@link ObjectMapping}
     */
    public ObjectMapping() {
        this.table = getClass().getAnnotation(Table.class);
        for (final Field field : getClass().getFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                mappings.add(new FieldMapping(getClass(), field, this));
            }
        }
    }
}
