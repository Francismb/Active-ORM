package org.activeorm.mapping;

import org.activeorm.User;
import org.activeorm.database.Database;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Francis on 3/05/16.
 * Project Jactive-Record.
 */
public class ActiveRecordTest {

    @Test
    public void testSave() {
        final Database database = Database.fromYaml("./config.yml");
        database.execute("CREATE TABLE users(user_id INTEGER PRIMARY KEY, username TEXT, password TEXT)", null);

        final User user = new User();
        user.name = "tom";
        assertTrue(user.save());
        assertTrue(user.destroy());

        database.execute("DROP TABLE users", null);
    }
}
