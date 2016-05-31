package org.activeorm;

import org.activeorm.database.Database;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Francis on 23/05/16.
 * Project Active-ORM.
 */
public class HasOneTest {

    @Test
    public void test() {
        final Database database = Database.fromYaml("./h2-config.yml");

        database.execute("CREATE TABLE users(user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))", null);
        database.execute("CREATE TABLE pies(pie_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT)", null);

        final User user = new User();
        user.name = "tom";
        user.save();

        final Pie pie = new Pie();
        pie.save();

        user.pie.set(pie);
        user.save();

        /**
         * Issue...
         * #1 - Set users relationship value to a pie object
         * #2 - if the user is persisted set the pies value 'user_id' to the users id
         * #3 - if the user is not persisted then when saving the user set the pie's user id
         */

        assertTrue(user.save());
    }

}
