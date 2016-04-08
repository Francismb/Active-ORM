package org.jactiverecord.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class PostgreSQLDatabaseConfiguration extends AuthenticatedDatabaseConfiguration {

    /**
     * Constructs a new {@link PostgreSQLDatabaseConfiguration}
     *
     * @param url      the url or location of the database
     * @param username the username to connect to the database
     * @param password the password to connect to the database
     */
    public PostgreSQLDatabaseConfiguration(String url, String username, String password) {
        super(url, username, password);
    }
}
