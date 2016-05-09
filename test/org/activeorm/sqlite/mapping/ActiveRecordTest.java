package org.activeorm.sqlite.mapping;

import org.activeorm.User;
import org.activeorm.database.Database;
import org.activeorm.query.Query;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Francis on 3/05/16.
 * Project Jactive-Record.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActiveRecordTest {

    @Test
    public void testASQLiteSave() {
        final Database database = Database.fromYaml("./sqllite-config.yml");

        database.execute("CREATE TABLE users(user_id INTEGER PRIMARY KEY, username TEXT, password TEXT)", null);

        final User user = new User();
        user.name = "tom";
        assertTrue(user.save());

        database.disconnect();
    }

    @Test
    public void testBSQLiteDestroy() {
        final Database database = Database.fromYaml("./sqllite-config.yml");

        final User user = Query.build(User.class).where("username").equalTo("tom").first();
        assertTrue(user.destroy());

        database.execute("DROP TABLE users", null);

        database.disconnect();
    }

}
