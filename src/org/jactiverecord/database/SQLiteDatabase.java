package org.jactiverecord.database;

import org.jactiverecord.database.configuration.SQLiteDatabaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class SQLiteDatabase implements Database {

    /**
     * The connection to the database if there is one.
     */
    private Connection connection = null;

    /**
     * The database configuration object which contains
     * connection info.
     */
    private final SQLiteDatabaseConfiguration configuration;

    /**
     * Constructs a new {@link SQLiteDatabase}
     *
     * @param configuration the database configuration object
     */
    public SQLiteDatabase(final SQLiteDatabaseConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("A SQLLiteDatabaseConfiguration is required");
        }
        this.configuration = configuration;
    }

    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + configuration.address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

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
