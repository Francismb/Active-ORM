package org.jactiverecord.orm;

import org.jactiverecord.orm.annotations.Table;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class ActiveRecord {

    private final Map<Field, Object> values = new HashMap<Field, Object>();

    public boolean save() {

    }

    public String getTableName() {
        final Table annotation = getClass().getAnnotation(Table.class);
        if (annotation != null) {
            return annotation.name();
        }
        return null;
    }

}
