package org.activerecord.query;

import org.activerecord.User;
import org.jactiverecord.database.Database;
import org.jactiverecord.query.Query;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Francis on 28/04/16.
 * Project Jactive-Record.
 */
public class QueryTest {

    @Test
    public void testQuery() {
        final Database database = Database.fromYaml("config.yml");
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
