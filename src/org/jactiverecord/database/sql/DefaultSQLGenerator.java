package org.jactiverecord.database.sql;

import org.jactiverecord.database.sql.adapters.ConditionAdapter;
import org.jactiverecord.database.sql.adapters.LimitAdapter;
import org.jactiverecord.database.sql.adapters.OrderAdapter;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 *
 * Default implementation of {@link SQLGenerator}.
 */
public class DefaultSQLGenerator implements SQLGenerator {

    public String select(final String table, final String[] columns, final ConditionAdapter[] conditions, final OrderAdapter[] order, final LimitAdapter limit) {
        if (table == null) {
            throw new NullPointerException("Table name cannot be null");
        }
        return null;
    }

    public String insert(final String table, final String[] columns) {
        if (table == null) {
            throw new NullPointerException("Table name cannot be null");
        }
        return null;
    }

    public String update(final String table, final String[] columns, final ConditionAdapter[] conditions) {
        if (table == null) {
            throw new NullPointerException("Table name cannot be null");
        }
        return null;
    }

    public String delete(final String table, final ConditionAdapter[] conditions) {
        if (table == null) {
            throw new NullPointerException("Table name cannot be null");
        }
        return null;
    }
}
