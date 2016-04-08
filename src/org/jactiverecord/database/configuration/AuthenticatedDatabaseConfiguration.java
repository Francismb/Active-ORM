package org.jactiverecord.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * A database configuration object containing information
 * on the authentication of the database
 */
public class AuthenticatedDatabaseConfiguration extends DatabaseConfiguration {

    /**
     * The username to use when connecting to the database
     */
    public final String username;

    /**
     * The password to use when connecting to the database
     */
    public final String password;

    /**
     * Constructs a new {@link AuthenticatedDatabaseConfiguration}
     *
     * @param url       the url or location of the database
     * @param username the username to connect to the database
     * @param password the password to connect to the database
     */
    public AuthenticatedDatabaseConfiguration(final String url, final String username, final String password) {
        super(url);
        this.username = username;
        this.password = password;
    }
}
