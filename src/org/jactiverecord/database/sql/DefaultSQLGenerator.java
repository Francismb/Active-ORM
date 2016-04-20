package org.jactiverecord.database.sql;

import jdk.nashorn.internal.objects.annotations.Where;
import org.jactiverecord.database.sql.expressions.WhereExpression;
import org.jactiverecord.database.sql.expressions.OrderExpression;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 *
 * Default implementation of {@link SQLGenerator}.
 */
public class DefaultSQLGenerator implements SQLGenerator {

    public String select(final String table, final String[] columns, final WhereExpression[] conditions, final OrderExpression[] orders, final boolean limit) {
        // Throw an exception if there is not table specified
        if (table == null || table.length() == 0) {
            throw new NullPointerException("Table name cannot be null");
        }

        // Create a string buffer
        final StringBuilder sql = new StringBuilder();

        // Append initial SELECT
        sql.append("SELECT ");

        // If columns have been defined then add the columns to the
        // expression but if they have not been defined just add a '*'
        if (columns != null && columns.length > 0) {
            for (int i = 0; i < columns.length; i++) {
                final String column = columns[i];

                // Add the condition to the sql
                sql.append('`').append(column).append('`');

                // If there is another condition after this
                // one add AND sql
                if ((i + 1) < columns.length) {
                    sql.append(", ");
                } else {
                    sql.append(" ");
                }
            }
        } else {
            sql.append("* ");
        }

        // If conditions have been defined then add the conditions
        // to the expression
        if (conditions != null && conditions.length > 0) {
            sql.append("WHERE ");
            for (int i = 0; i < conditions.length; i++) {
                final WhereExpression condition = conditions[i];

                // Add the condition to the sql
                sql.append('`').append(condition.column).append('`').append(" ").append(condition.operator).append(" ? ");

                // If there is another condition after this
                // one add AND sql
                if ((i + 1) < conditions.length) {
                    sql.append("AND ");
                }
            }
        }

        // If orders has been defined then add the orders to
        // to the expression
        if (orders != null && orders.length > 0) {
            sql.append("ORDER BY ");
            for (int i = 0; i < orders.length; i++) {
                final OrderExpression order = orders[i];

                // Add the condition to the sql
                sql.append('`').append(order.column).append('`').append(" ").append(order.direction);

                // If there is another condition after this
                // one add AND sql
                if ((i + 1) < orders.length) {
                    sql.append(", ");
                } else {
                    sql.append(" ");
                }
            }
        }

        // If the limit is set then add the limit sql
        if (limit) {
            sql.append("LIMIT ?");
        }

        // Return the sql and trim and leading or trailing whitespace
        return sql.toString().trim();
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
