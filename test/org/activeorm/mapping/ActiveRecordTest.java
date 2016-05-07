package org.activeorm.mapping;

import org.activeorm.User;
import org.activeorm.database.Database;
import org.activeorm.query.Query;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Francis on 3/05/16.
 * Project Jactive-Record.
 */
public class ActiveRecordTest {

    @Test
    public void testSQLiteSave() {
        final Database database = Database.fromYaml("./sqllite-config.yml");

        database.execute("CREATE TABLE users(user_id INTEGER PRIMARY KEY, username TEXT, password TEXT)", null);

        final User user = new User();
        user.name = "tom";
        assertTrue(user.save());
        assertTrue(user.destroy());

        database.execute("DROP TABLE users", null);

        database.disconnect();
    }

    @Test
    public void testH2Save() {
        final Database database = Database.fromYaml("./h2-config.yml");

        database.execute("CREATE MEMORY TABLE users(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))", null);

        User user = new User();
        user.name = "tom";
        assertTrue(user.save());
        assertTrue(user.destroy());

        database.execute("DROP TABLE users", null);

        database.disconnect();
    }
}
