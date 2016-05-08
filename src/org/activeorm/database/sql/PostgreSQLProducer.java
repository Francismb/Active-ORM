package org.activeorm.database.sql;

/**
 * Created by Francis on 8/05/16.
 * Project Active-ORM.
 */
public class PostgreSQLProducer extends DefaultSQLProducer {

    public PostgreSQLProducer() {
        delimiter = "\"";
    }
}
