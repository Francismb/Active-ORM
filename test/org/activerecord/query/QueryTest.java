package org.activerecord.query;

import org.activerecord.User;
import org.jactiverecord.database.Database;
import org.jactiverecord.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * Created by Francis on 28/04/16.
 * Project Jactive-Record.
 */
public class QueryTest {

    @Test
    public void testQuery() {
        Database.fromYaml("config.yml");

        final User user = Query.build(User.class).first();
        System.out.println(user.password);
    }
}
