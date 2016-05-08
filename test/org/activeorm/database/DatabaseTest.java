package org.activeorm.database;

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
    public void loadSQLiteDatabase() {
        final Database database = Database.fromYaml("./sqllite-config.yml");
        assertNotNull(database);
    }

    @Test
    public void loadHSQLDatabase() {
        final Database database = Database.fromYaml("./h2-config.yml");
        assertNotNull(database);
    }

    @Test
    public void testSQLiteRawQuery() throws SQLException {
        final Database database = Database.fromYaml("./sqllite-config.yml");
        database.execute("CREATE TABLE users(user_id INTEGER PRIMARY KEY, username TEXT, password TEXT)", null);
        assertEquals(1, database.execute(database.sql.insert("users", new String[]{"username", "password"}), new Object[]{"Jackson", "super secret password"}), 1);
        final ResultSet rs = database.query(database.sql.select("users", null, new String[]{"username"}, new String[]{"="}, null, null, false), new Object[]{"Jackson"});
        assertNotNull(rs);
        rs.close();
        database.execute("DROP TABLE users", null);
        database.disconnect();
    }

    @Test
    public void testH2RawQuery() throws SQLException {
        final Database database = Database.fromYaml("./h2-config.yml");
        database.execute("CREATE MEMORY TABLE users(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))", null);
        assertEquals(1, database.execute(database.sql.insert("users", new String[]{"username", "password"}), new Object[]{"Jackson", "super secret password"}), 1);
        final ResultSet rs = database.query(database.sql.select("users", null, new String[]{"username"}, new String[]{"="}, null, null, false), new Object[]{"Jackson"});
        assertNotNull(rs);
        rs.close();
        database.execute("DROP TABLE users", null);
        database.disconnect();
    }
}
