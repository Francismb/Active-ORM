package org.activeorm.database;

import org.activeorm.database.configuration.PostgreSQLDatabaseConfiguration;
import org.activeorm.database.sql.DefaultSQLProducer;
import org.activeorm.database.sql.PostgreSQLProducer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Francis on 8/05/16.
 * Project Active-ORM.
 *
 * A PostgreSQL {@link Database} implementation.
 */
public class PostgreSQLDatabase extends Database {

    /**
     * Constructs a new {@link PostgreSQLDatabase}.
     */
    public PostgreSQLDatabase(final PostgreSQLDatabaseConfiguration configuration) {
        super(configuration, new PostgreSQLProducer());
    }

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + configuration.address + "/" + configuration.name, configuration.username, configuration.password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
