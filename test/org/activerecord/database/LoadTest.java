package org.activerecord.database;

import org.jactiverecord.database.Database;
import org.junit.Test;

/**
 * Created by Francis on 30/04/16.
 * Project Jactive-Record.
 */
public class LoadTest {

    @Test
    public void loadYaml() {
        final Database database = Database.fromYaml("./config.yml");
    }

}
