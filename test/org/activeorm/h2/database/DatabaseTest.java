package org.activeorm.h2.database;

import org.activeorm.database.Database;
import org.activeorm.utility.Resource;
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
    public void loadHSQLDatabase() {
        final Database database = Database.fromYaml("./h2-config.yml");
        assertNotNull(database);
    }

    @Test
    public void testH2RawQuery() throws SQLException {
        final Database database = Database.fromYaml("./h2-config.yml");
        database.execute("CREATE MEMORY TABLE users(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))", null);
        assertEquals(1, database.execute(database.sql.insert("users", new String[]{"username", "password"}), new Object[]{"Jackson", "super secret password"}));
        final Resource resource = database.query(database.sql.select("users", null, new String[]{"username"}, new String[]{"="}, null, null, false), new Object[]{"Jackson"});
        assertNotNull(resource);
        resource.release();
        database.execute("DROP TABLE users", null);
        database.disconnect();
    }
}
