package org.jactiverecord.mapping;

import org.jactiverecord.database.sql.DefaultSQLGenerator;
import org.jactiverecord.mapping.FieldMapping;
import org.jactiverecord.mapping.ObjectMapping;
import org.jactiverecord.mapping.annotations.Column;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class ActiveRecord extends ObjectMapping {

    private final DefaultSQLGenerator generator = new DefaultSQLGenerator();

    public boolean save() {
        final List<FieldMapping> modifications = new ArrayList<FieldMapping>();
        for (final FieldMapping mapping : mappings) {
            if (mapping.hasBeenModified()) {
                modifications.add(mapping);
            }
        }

        final String[] columns = new String[modifications.size()];
        for (int i = 0; i < modifications.size(); i++) {
            columns[i] = modifications.get(i).column.name();
        }

        final String sql;
        if (persisted) {
            sql = generator.update(table.name(), columns, new String[]{primaryKey.column.name()}, new String[]{"="});
        } else {
            sql = generator.insert(table.name(), columns);
        }

        return true;
    }

}
