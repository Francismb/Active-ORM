package org.jactiverecord.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class PostgreSQLDatabaseConfiguration extends DatabaseConfiguration {

    /**
     * Constructs a new {@link PostgreSQLDatabaseConfiguration}
     *
     * @param address  the address or location of the database
     * @param username the username to connect to the database
     * @param password the password to connect to the database
     */
    public PostgreSQLDatabaseConfiguration(final String address, final String username, final String password) {
        super(address, username, password);
    }
}
