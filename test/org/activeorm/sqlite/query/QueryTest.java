package org.activeorm.sqlite.query;

import org.activeorm.User;
import org.activeorm.database.Database;
import org.activeorm.query.Query;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Francis on 28/04/16.
 * Project Jactive-Record.
 */
public class QueryTest {

    @Test
    public void testSQLiteQuery() {
        final Database database = Database.fromYaml("sqllite-config.yml");
        database.execute("CREATE TABLE users(user_id INTEGER PRIMARY KEY, username TEXT, password TEXT)", null);

        final User tom = new User();
        tom.name = "Tom";
        tom.save();

        assertEquals("Tom", Query.build(User.class).where("username").equalTo("Tom").first().name);
        assertEquals(null, Query.build(User.class).where("username").equalTo("Chimp").first());

        tom.destroy();

        database.execute("DROP TABLE users", null);
    }

}
