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

    /**
     * The {@link ObjectMapping} used to map the object.
     * Constructed in the constructor of {@link ActiveRecord}.
     */
    private final ObjectMapping mapper;

    /**
     * Constructs a new {@link ActiveRecord}.
     */
    public ActiveRecord() {
        this.mapper = new ObjectMapping(getClass());
    }

    /**
     * Either executes a insert or update sql statement
     * depending on if the object has been persisted yet.
     *
     * @return true if successfully executed the statement else false.
     */
    public boolean save() {
        // Find all the modified fields
        final List<FieldMapping> modifications = new ArrayList<>();
        for (final FieldMapping mapping : mapper.mappings) {
            if (mapping.hasBeenModified()) {
                modifications.add(mapping);
            }
        }

        // Create a array of column names based off the modifed fields
        final String[] columns = new String[modifications.size()];
        for (int i = 0; i < modifications.size(); i++) {
            columns[i] = modifications.get(i).column.name();
        }

        // The values of the modified fields
        final Object[] values = new Object[modifications.size()];
        for (int i = 0; i < modifications.size(); i++) {
            values[i] = modifications.get(i).getValue();
        }

        // Generate the sql for the statement
        final String sql;
        if (mapper.persisted) {
            sql = Database.getInstance().sql.update(mapper.table.name(), columns, new String[]{mapper.primaryKey.column.name()}, new String[]{"="});
        } else {
            sql = Database.getInstance().sql.insert(mapper.table.name(), columns);
        }

        // Execute the sql
        final int result = Database.getInstance().execute(sql, values);

        // If the sql had effect return true
        return result > 0;
    }

    /**
     * Creates a duplicate object which is not persisted.
     * It will not contain a primary key.
     *
     * @return true if successfully duplicated else false.
     */
    public boolean duplicate() {
        try {
            final Object clone = this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return false;
    }

}
