package org.jactiverecord.sql;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 */
public interface SQLGenerator {

    // SELECT column_1, column_2 WHERE id = 2 AND name = "NAME" ORDER BY date_created ASC LIMIT 1
    public String select(final String[] columns);

    // INSERT INTO table (columns_1, column_2) VALUES (value_1, value_2)
    public String insert();

    // UPDATE users SET column_1 = value_1, column_2 = value_2 WHERE ID = 2 AND name = "NAME"
    public String update();

    // DELETE FROM users WHERE id = 2 AND name = "NAME"
    public String delete();

}
