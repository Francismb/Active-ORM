package org.activeorm.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * A PostgreSQL {@link DatabaseConfiguration} implementation.
 */
public class PostgreSQLDatabaseConfiguration extends DatabaseConfiguration {

    /**
     * Constructs a new {@link PostgreSQLDatabaseConfiguration}
     *
     * @param name     the name of the database
     * @param address  the address or location of the database
     * @param username the username to connect to the database
     * @param password the password to connect to the database
     */
    public PostgreSQLDatabaseConfiguration(final String name, final String address, final String username, final String password) {
        super(name, address, username, password);
    }
}
