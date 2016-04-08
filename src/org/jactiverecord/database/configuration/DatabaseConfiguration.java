package org.jactiverecord.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * A database configuration object containing information
 * on what to use to connect to the database
 */
public abstract class DatabaseConfiguration {

    /**
     * The ip address or location of the database
     */
    private final String ip;

    /**
     * The username to use when connecting to the database
     */
    private final String username;

    /**
     * The password to use when connecting to the database
     */
    private final String password;

    /**
     * Constructs a new {@link DatabaseConfiguration}
     * @param ip the ip address or location of the database
     * @param username the username to connect to the database
     * @param password the password to connect to the database
     */
    public DatabaseConfiguration(final String ip, final String username, final String password) {
        this.ip = ip;
        this.username = username;
        this.password = password;
    }
}
