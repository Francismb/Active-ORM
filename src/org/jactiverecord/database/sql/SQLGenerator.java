package org.jactiverecord.database.sql;

import org.jactiverecord.database.sql.adapters.ConditionAdapter;
import org.jactiverecord.database.sql.adapters.LimitAdapter;
import org.jactiverecord.database.sql.adapters.OrderAdapter;

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
     * @param conditions an array of {@link ConditionAdapter} for conditions.
     * @param order an array of {@link OrderAdapter} for ordering of results.
     * @param limit the limit of results.
     * @return the appropriate sql for the select statement.
     */
    public String select(final String table, final String[] columns, final ConditionAdapter[] conditions, final OrderAdapter[] order, final LimitAdapter limit);


    /**
     * Generates the appropriate sql for a insert statement.
     * INSERT INTO table (columns_1, column_2) VALUES (value_1, value_2)
     * @param table the name of the table.
     * @param columns the columns to insert data into.
     * @param values the values to insert into the columns.
     * @return the appropriate sql for the insert statement.
     */
    public String insert(final String table, final String[] columns, final String[] values);

    /**
     * Generates the appropriate sql for a update statement.
     * UPDATE users SET column_1 = value_1, column_2 = value_2 WHERE ID = 2 AND name = "NAME"
     * @param table the name of the table.
     * @param columns the columns to update data in.
     * @param values the values to update into the columns.
     * @param conditions an array of {@link ConditionAdapter} for conditions.
     * @return the appropriate sql for the update statement.
     */
    public String update(final String table, final String[] columns, final String[] values, final ConditionAdapter[] conditions);

    /**
     * Generates the appropriate sql for a delete statement.
     * DELETE FROM users WHERE id = 2 AND name = "NAME"
     * @param table the name of the table.
     * @param conditions an array of {@link ConditionAdapter} for conditions.
     * @return the appropriate sql for the delete statement.
     */
    public String delete(final String table, final ConditionAdapter[] conditions);

}
