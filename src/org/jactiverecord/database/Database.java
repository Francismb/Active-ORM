package org.jactiverecord.database;

import org.jactiverecord.database.configuration.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public abstract class Database {

    /**
     * The connection to the database if there is one.
     */
    protected final Connection connection;

    /**
     * The database configuration object which contains
     * connection info.
     */
    protected final DatabaseConfiguration configuration;

    /**
     * Constructs a new {@link Database}.
     * Connects to the database when the object is constructed
     *
     * @param configuration the database configuration
     */
    public Database(final DatabaseConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("A SQLLiteDatabaseConfiguration is required");
        }
        this.configuration = configuration;
        connection = connect();
    }

    /**
     * Establishes a {@link Connection} to the database
     * @return the {@link Connection} that was created or null
     * if it failed to create a connection.
     */
    public abstract Connection connect();

    /**
     * Disconnects to the database
     * @return true if successfully disconnected else false.
     */
    public boolean disconnect() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
