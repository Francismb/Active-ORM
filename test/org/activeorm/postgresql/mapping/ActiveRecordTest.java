package org.activeorm.postgresql.mapping;

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
