package org.activeorm.mapping;

import org.activeorm.User;
import org.activeorm.database.Database;
import org.activeorm.query.Query;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.Assert.assertEquals;
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

    @Test
    public void testCH2Save() {
        final Database database = Database.fromYaml("./h2-config.yml");

        database.execute("CREATE TABLE users(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))", null);

        User user = new User();
        user.name = "tom";

        assertTrue(user.save());

        database.disconnect();
    }

    @Test
    public void testDH2Destroy() {
        final Database database = Database.fromYaml("./h2-config.yml");

        database.execute("CREATE TABLE users(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))", null);

        User user = new User();
        user.name = "tom";
        user.save();
        user = null;

        user = Query.build(User.class).where("username").equalTo("tom").first();
        assertTrue(user.destroy());

        database.execute("DROP TABLE users", null);

        database.disconnect();
    }

    @Test
    public void testEPostgresSave() {
        final Database database = Database.fromYaml("./postgre-config.yml");

        database.execute("CREATE TABLE users(user_id serial PRIMARY KEY, username VARCHAR(50), password VARCHAR(50))", null);

        User user = new User();
        user.name = "tom";

        assertTrue(user.save());

        database.disconnect();
    }

    @Test
    public void testFPostgresDestroy() {
        final Database database = Database.fromYaml("./postgre-config.yml");

        final User user = Query.build(User.class).where("username").equalTo("tom").first();
        assertTrue(user.destroy());

        database.execute("DROP TABLE users", null);

        database.disconnect();
    }
}
