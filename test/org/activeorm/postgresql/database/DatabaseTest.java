package org.activeorm.postgresql.database;

import org.activeorm.database.Database;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Francis on 30/04/16.
 * Project Jactive-Record.
 */
public class DatabaseTest {

    @Test
    public void loadPostgreSQLDatabase() {
        final Database database = Database.fromYaml("./postgre-config.yml");
        assertNotNull(database);
    }

    @Test
    public void testPostgreSQLRawQuery() throws SQLException {
        final Database database = Database.fromYaml("./postgre-config.yml");
        database.execute("CREATE TABLE users(id serial PRIMARY KEY, username VARCHAR(50), password VARCHAR(50))", null);
        assertEquals(1, database.execute(database.sql.insert("users", new String[]{"username", "password"}), new Object[]{"Jackson", "super secret password"}));
        final ResultSet rs = database.query(database.sql.select("users", null, new String[]{"username"}, new String[]{"="}, null, null, false), new Object[]{"Jackson"});
        assertNotNull(rs);
        rs.close();
        database.execute("DROP TABLE users", null);
        database.disconnect();
    }
}
