package org.jactiverecord.mapping;

import org.jactiverecord.database.Database;
import org.jactiverecord.database.sql.DefaultSQLProducer;
import org.jactiverecord.database.sql.SQLProducer;
import org.jactiverecord.mapping.FieldMapping;
import org.jactiverecord.mapping.ObjectMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class ActiveRecord {

    private final ObjectMapping mapper;

    public ActiveRecord() {
        this.mapper = new ObjectMapping(getClass());
    }

    public boolean save() {
        final List<FieldMapping> modifications = new ArrayList<FieldMapping>();
        for (final FieldMapping mapping : mapper.mappings) {
            if (mapping.hasBeenModified()) {
                modifications.add(mapping);
            }
        }

        final String[] columns = new String[modifications.size()];
        for (int i = 0; i < modifications.size(); i++) {
            columns[i] = modifications.get(i).column.name();
        }

        final String sql;
        if (mapper.persisted) {
            sql = Database.getInstance().sqlProducer.update(mapper.table.name(), columns, new String[]{mapper.primaryKey.column.name()}, new String[]{"="});
        } else {
            sql = Database.getInstance().sqlProducer.insert(mapper.table.name(), columns);
        }

        return true;
    }

}
