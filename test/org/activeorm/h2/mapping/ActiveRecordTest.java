package org.activeorm.h2.mapping;

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
    public void testAH2Save() {
        final Database database = Database.fromYaml("./h2-config.yml");

        database.execute("CREATE TABLE users(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))", null);

        User user = new User();
        user.name = "tom";

        assertTrue(user.save());

        database.disconnect();
    }

    @Test
    public void testBH2Destroy() {
        final Database database = Database.fromYaml("./h2-config.yml");

        database.execute("CREATE TABLE users(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))", null);

        User user = new User();
        user.name = "tom";
        user.save();
        user = null;

        user = Query.build(User.class).where("username").equalTo("tom").first();
        assertTrue(user.destroy());

        database.disconnect();
    }

}
