package org.activeorm.database.sql;

/**
 * Created by Francis on 13/04/16.
 * Project Active-ORM.
 *
 * A interface which provides functionality to produce basic sql queries.
 */
public interface SQLProducer {

    /**
     * Generates the appropriate sql for a select statement.
     *
     * @param table the name of the table.
     * @param columns the columns to retrieve data from.
     * @param whereColumns the column names used in the where statement.
     * @param whereOperators a array of where operators(=, >, <, >=, <=) used in the where statement.
     * @param orderColumns the column names used to order the results.
     * @param orderOperators the order operators(ASC, DESC) used to order the results.
     * @param limit limit the results.
     * @return the appropriate sql for a select statement.
     */
    public String select(final String table, final String[] columns, final String[] whereColumns, final String[] whereOperators, final String[] orderColumns, final String[] orderOperators, final boolean limit);

    /**
     * Generates the appropriate sql for a insert statement.
     *
     * @param table the name of the table.
     * @param columns the columns to insert data into.
     * @return the appropriate sql for a insert statement.
     */
    public String insert(final String table, final String[] columns);

    /**
     * Generates the appropriate sql for a update statement.
     *
     * @param table the name of the table.
     * @param columns the columns to update data in.
     * @param whereColumns the column names used in the where statement.
     * @param whereOperators a array of where operators(=, >, <, >=, <=) used in the where statement.
     * @return the appropriate sql for a update statement.
     */
    public String update(final String table, final String[] columns, final String[] whereColumns, final String[] whereOperators);

    /**
     * Generates the appropriate sql for a delete statement.
     *
     * @param table the name of the table.
     * @param whereColumns the column names used in the where statement.
     * @param whereOperators a array of where operators(=, >, <, >=, <=) used in the where statement.
     * @return the appropriate sql for a delete statement.
     */
    public String delete(final String table, final String[] whereColumns, final String[] whereOperators);

    /**
     * Generates the 'WHERE' part of a sql statement,
     *
     * @param columns a array of column names used in the where statement.
     * @param operators a array of operators(=, >, <, >=, <=) used in the where statement.
     * @return The where sql to be inserted into a larger statement
     */
    public String where(final String[] columns, final String[] operators);

    /**
     * Generates the 'ORDER' part of a sql statement.
     *
     * @param columns a array of column names used in the order statement.
     * @param operators a array of operators(ASC, DESC) used in the order statement.
     * @return The order sql to be inserted into a larger statement
     */
    public String order(final String[] columns, final String[] operators);

}
