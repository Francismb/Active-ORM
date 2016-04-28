package org.jactiverecord.database.sql;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 *
 * A interface which provides functions to build basic sql queries.
 */
public interface SQLGenerator {

    /**
     * Generates the appropriate sql for a select statement.
     * SELECT column_1, column_2 WHERE id = 2 AND name = "NAME" ORDER BY date_created ASC LIMIT 1
     * @param table the name of the table.
     * @param columns the columns to get data from or null for all columns.
     * @param whereColumns a array of column names used in the where statement.
     * @param whereOperators a array of operators(=, >, <, >=, <=) used in the where statement.
     * @param limit the limit of results.
     * @return the appropriate sql for the select statement.
     */
    public String select(final String table, final String[] columns, final String[] whereColumns, final String[] whereOperators, final String[] orderColumns, final String[] orderOperators, final boolean limit);

    /**
     * Generates the appropriate sql for a insert statement.
     * INSERT INTO table (columns_1, column_2) VALUES (value_1, value_2)
     * @param table the name of the table.
     * @param columns the columns to insert data into.
     * @return the appropriate sql for the insert statement.
     */
    public String insert(final String table, final String[] columns);

    /**
     * Generates the appropriate sql for a update statement.
     * UPDATE users SET column_1 = value_1, column_2 = value_2 WHERE ID = 2 AND name = "NAME"
     * @param table the name of the table.
     * @param columns the columns to update data in.
     * @param whereColumns a array of column names used in the where statement.
     * @param whereOperators a array of operators(=, >, <, >=, <=) used in the where statement.
     * @return the appropriate sql for the update statement.
     */
    public String update(final String table, final String[] columns, final String[] whereColumns, final String[] whereOperators);

    /**
     * Generates the appropriate sql for a delete statement.
     * DELETE FROM users WHERE id = 2 AND name = "NAME"
     * @param table the name of the table.
     * @param whereColumns a array of column names used in the where statement.
     * @param whereOperators a array of operators(=, >, <, >=, <=) used in the where statement.
     * @return the appropriate sql for the delete statement.
     */
    public String delete(final String table, final String[] whereColumns, final String[] whereOperators);

    /**
     * Generates the where piece of sql to be placed inside
     * of another sql statement
     * @param columns a array of column names used in the where statement.
     * @param operators a array of operators(=, >, <, >=, <=) used in the where statement.
     * @return The where sql to be inserted into a larger statement
     */
    public String where(final String[] columns, final String[] operators);

    /**
     * Generates the order piece of sql to be placed inside
     * of another sql statement
     * @param columns a array of column names used in the order statement.
     * @param operators a array of operators(ASC, DESC) used in the order statement.
     * @return The order sql to be inserted into a larger statement
     */
    public String order(final String[] columns, final String[] operators);

}
