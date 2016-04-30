package org.activerecord;

import org.jactiverecord.database.Database;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class GeneralTest {

    @Test
    public void test() {
        final Database database = Database.fromYaml("config.yml");
        database.execute("DROP TABLE users", null);
        database.execute("CREATE TABLE users(id int, username varchar(255), password varchar(30))", null);

        final UserModel model = new UserModel();
        model.name = "tom";
        model.password = "This is teh super secret password";
        System.out.println(model.save());
    }

}
