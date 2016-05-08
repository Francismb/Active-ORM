package org.activeorm.database.configuration;

/**
 * Created by Francis on 8/05/16.
 * Project Active-ORM.
 */
public class PostgreSQLDatabaseConfiguration extends DatabaseConfiguration {

    /**
     * Constructs a new {@link DatabaseConfiguration}
     *
     * @param name     the name of the database
     * @param address  the address of the database
     * @param username the username to connect to the database
     * @param password the password to connect to the database
     */
    public PostgreSQLDatabaseConfiguration(final String name, final String address, final String username, final String password) {
        super(name, address, username, password);
    }
}
