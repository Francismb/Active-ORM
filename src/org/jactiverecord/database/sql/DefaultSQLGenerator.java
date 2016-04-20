package org.jactiverecord.database.sql;

import org.jactiverecord.database.sql.expressions.WhereExpression;
import org.jactiverecord.database.sql.expressions.LimitExpression;
import org.jactiverecord.database.sql.expressions.OrderExpression;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 *
 * Default implementation of {@link SQLGenerator}.
 */
public class DefaultSQLGenerator implements SQLGenerator {

    public String select(final String table, final String[] columns, final WhereExpression[] conditions, final OrderExpression[] order, final LimitExpression limit) {
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

    public String update(final String table, final String[] columns, final WhereExpression[] conditions) {
        if (table == null) {
            throw new NullPointerException("Table name cannot be null");
        }
        return null;
    }

    public String delete(final String table, final WhereExpression[] conditions) {
        if (table == null) {
            throw new NullPointerException("Table name cannot be null");
        }
        return null;
    }
}
