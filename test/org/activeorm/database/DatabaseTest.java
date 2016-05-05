package org.activeorm.database;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Francis on 30/04/16.
 * Project Jactive-Record.
 */
public class DatabaseTest {

    @Test
    public void loadDatabase() {
        final Database database = Database.fromYaml("./config.yml");
        assertNotNull(database);
    }

    @Test
    public void testRawQuery() {
        final Database database = Database.fromYaml("./config.yml");
        database.execute("CREATE TABLE users(user_id INTEGER PRIMARY KEY, username TEXT, password TEXT)", null);
        assertEquals(1, database.execute(database.sql.insert("users", new String[]{"username", "password"}), new Object[]{"Jackson", "super secret password"}), 1);
        assertNotNull(database.query(database.sql.select("users", null, new String[]{"username"}, new String[]{"="}, null, null, false), new Object[]{"Jackson"}));
        database.execute("DROP TABLE users", null);
    }

}
