package org.jactiverecord.mapping;

import org.jactiverecord.database.Database;

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
    private final ObjectMapping mapping;

    /**
     * Constructs a new {@link ActiveRecord}.
     */
    public ActiveRecord() {
        this.mapping = new ObjectMapping(getClass(), this);
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
        for (final FieldMapping mapping : this.mapping.mappings) {
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
        if (mapping.persisted) {
            sql = Database.getInstance().sql.update(mapping.table.name(), columns, new String[]{mapping.primaryKey.column.name()}, new String[]{"="});
        } else {
            sql = Database.getInstance().sql.insert(mapping.table.name(), columns);
        }

        // Execute the sql
        final int result = Database.getInstance().execute(sql, values, mapping.primaryKey);

        // If the sql had effect return true
        return result > 0;
    }

    /**
     * Deletes the record from the database.
     *
     * @return true if successful, else false.
     */
    public boolean destroy() {
        return false;
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
