package org.jactiverecord.database.sql;

import jdk.nashorn.internal.objects.annotations.Where;
import org.jactiverecord.database.sql.expressions.WhereExpression;
import org.jactiverecord.database.sql.expressions.OrderExpression;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 * <p>
 * Default implementation of {@link SQLGenerator}.
 */
public class DefaultSQLGenerator implements SQLGenerator {

    public String select(final String table, final String[] columns, final WhereExpression[] conditions, final OrderExpression[] orders, final boolean limit) {
        // Throw an exception if there is not table specified
        if (table == null || table.length() == 0) {
            throw new IllegalArgumentException("Table name must be specified");
        }

        // Create a string buffer
        final StringBuilder sql = new StringBuilder();

        // Append initial SELECT
        sql.append("SELECT ");

        // If columns have been defined then add the columns to the
        // sql but if they have not been defined just add a '*'
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

        // Add the from table sql
        sql.append("FROM ").append("`").append(table).append("`").append(" ");

        // If conditions have been defined then add the conditions
        // to the sql
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
        // to the sql
        if (orders != null && orders.length > 0) {
            sql.append("ORDER BY ");
            for (int i = 0; i < orders.length; i++) {
                final OrderExpression order = orders[i];

                // Add the condition to the sql
                sql.append('`').append(order.column).append('`').append(" ").append(order.direction);

                // If there is another order after this
                // one add a ', ' to the sql
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
        // Throw an exception if there is not table specified
        if (table == null || table.length() == 0) {
            throw new IllegalArgumentException("Table name must be specified");
        }

        // Throw an exception if there is no columns specified
        if (columns == null || columns.length == 0) {
            throw new IllegalArgumentException("More then one column must be specified");
        }

        // Create a string buffer
        final StringBuilder sql = new StringBuilder();

        // Append initial INSERT INTO table sql
        sql.append("INSERT INTO ").append("`").append(table).append("` ");

        // add the columns to the sql
        sql.append("(");
        for (int i = 0; i < columns.length; i++) {
            final String column = columns[i];

            // Add the condition to the sql
            sql.append('`').append(column).append('`');

            // If there is another column after this
            // one then add ', ' to the sql
            if ((i + 1) < columns.length) {
                sql.append(", ");
            }
        }
        sql.append(") ");

        sql.append("VALUES (");
        for (int i = 0; i < columns.length; i++) {

            // Append column value place holder
            sql.append("?");

            /// If there is another column after this
            // one then add ', ' to the sql
            if ((i + 1) < columns.length) {
                sql.append(", ");
            }
        }
        sql.append(')');

        // Return the sql and trim and leading or trailing whitespace
        return sql.toString().trim();
    }

    public String update(final String table, final String[] columns, final WhereExpression[] conditions) {
        // Throw an exception if there is not table specified
        if (table == null || table.length() == 0) {
            throw new IllegalArgumentException("Table name must be specified");
        }

        // Throw an exception if there is no columns specified
        if (columns == null || columns.length == 0) {
            throw new IllegalArgumentException("More then one column must be specified");
        }

        // Create a string buffer
        final StringBuilder sql = new StringBuilder();

        // Append initial UPDATE sql
        sql.append("UPDATE ");

        // Append table name
        sql.append("`").append(table).append("` ");

        // Add the columns to the sql
        sql.append("SET ");
        for (int i = 0; i < columns.length; i++) {
            final String column = columns[i];

            // Add the condition to the sql
            sql.append('`').append(column).append('`').append(" = ?");

            // If there is another column after this
            // one then add ', ' to the sql
            if ((i + 1) < columns.length) {
                sql.append(", ");
            } else {
                sql.append(" ");
            }
        }

        // If conditions have been defined then add the conditions
        // to the sql
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

        // Return the sql and trim and leading or trailing whitespace
        return sql.toString().trim();
    }

    public String delete(final String table, final WhereExpression[] conditions) {
        if (table == null) {
            throw new NullPointerException("Table name cannot be null");
        }
        return null;
    }
}
