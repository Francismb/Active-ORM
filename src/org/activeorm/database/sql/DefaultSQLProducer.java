package org.activeorm.database.sql;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 *
 * Default implementation of {@link SQLProducer}.
 */
public class DefaultSQLProducer implements SQLProducer {

    public String select(final String table, final String[] columns, final String[] whereColumns, final String[] whereOperators, final String[] orderColumns, final String[] orderOperators, final boolean limit) {
        // Throw an exception if there is not table specified
        if (table == null) {
            throw new NullPointerException("Table name must be specified");
        }

        // Throw and exception if the length of whereColumns and whereOperations is different
        if (whereColumns != null && whereOperators != null && whereColumns.length != whereOperators.length) {
            throw new IllegalArgumentException("whereColumns and whereOperators need to contain the same amount of elements");
        }

        // Throw and exception if the length of orderColumns and orderOperators is different
        if (orderColumns != null && orderOperators != null && orderColumns.length != orderOperators.length) {
            throw new IllegalArgumentException("orderColumns and orderOperators need to contain the same amount of elements");
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
        sql.append("FROM ").append('`').append(table).append('`').append(" ");

        // Add the where sql to the statement
        sql.append(where(whereColumns, whereOperators));

        // Add the order sql to the statement
        sql.append(order(orderColumns, orderOperators));

        // If the limit is set then add the limit sql
        if (limit) {
            sql.append("LIMIT ?");
        }

        // Return the sql and trim and leading or trailing whitespace
        return sql.toString().trim();
    }

    public String insert(final String table, final String[] columns) {
        // Throw an exception if there is not table specified
        if (table == null) {
            throw new NullPointerException("Table name must be specified");
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
        sql.append('(');
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
            sql.append('?');

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

    public String update(final String table, final String[] columns, final String[] whereColumns, final String[] whereOperators) {
        // Throw an exception if there is not table specified
        if (table == null) {
            throw new NullPointerException("Table name must be specified");
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
        sql.append('`').append(table).append("` ");

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

        // Add the where sql to the statement
        sql.append(where(whereColumns, whereOperators));

        // Return the sql and trim and leading or trailing whitespace
        return sql.toString().trim();
    }

    public String delete(final String table, final String[] whereColumns, final String[] whereOperators) {
        // Throw an exception if there is not table specified
        if (table == null) {
            throw new NullPointerException("Table name cannot be null");
        }

        // Create a string buffer
        final StringBuilder sql = new StringBuilder();

        // Append initial UPDATE sql
        sql.append("DELETE FROM ");

        // Append table name
        sql.append('`').append(table).append('`').append(" ");

        // Append where sql
        sql.append(where(whereColumns, whereOperators));

        return sql.toString().trim();
    }

    public String where(final String[] columns, final String[] operators) {
        // If columns or operators is not specified just return an empty string.
        if ((columns == null || operators == null) || columns.length == 0 && operators.length == 0) {
            return "";
        }

        // If the length of columns or operators is different throw an exception
        if (columns.length != operators.length) {
            throw new IllegalArgumentException("column length needs to be the same as the operator length");
        }

        // The where statement sql
        final StringBuilder sql = new StringBuilder();

        // Generate the sql
        sql.append("WHERE ");
        for (int i = 0; i < columns.length; i++) {
            final String column = columns[i];
            final String operator = operators[i];

            // Add the condition to the sql

            sql.append('`').append(column).append('`').append(" ").append(operator).append(" ? ");

            // If there is another condition after this
            // one add AND sql
            if ((i + 1) < columns.length) {
                sql.append("AND ");
            }
        }

        // Return the sql
        return sql.toString();
    }

    public String order(final String[] columns, final String[] operators) {
        // If columns or operators is not specified just return an empty string.
        if ((columns == null || operators == null) || columns.length == 0 && operators.length == 0) {
            return "";
        }

        // If the length of columns or operators is different throw an exception
        if (columns.length != operators.length) {
            throw new IllegalArgumentException("column length needs to be the same as the operator length");
        }

        // The order statement sql
        final StringBuilder sql = new StringBuilder();

        sql.append("ORDER BY ");
        for (int i = 0; i < columns.length; i++) {
            final String column = columns[i];
            final String operator = operators[i];

            // Add the condition to the sql
            sql.append('`').append(column).append('`').append(" ").append(operator);

            // If there is another order after this
            // one add a ', ' to the sql
            if ((i + 1) < columns.length) {
                sql.append(", ");
            } else {
                sql.append(" ");
            }
        }

        // Return the sql
        return sql.toString();
    }
}
