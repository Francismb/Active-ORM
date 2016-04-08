package org.jactiverecord.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * A database configuration object containing information
 * on were the database is located and authentication info.
 */
public abstract class DatabaseConfiguration {

    /**
     * The address of the database
     */
    public final String address;

    /**
     * The username to use when connecting to the database
     */
    public final String username;

    /**
     * The password to use when connecting to the database
     */
    public final String password;

    /**
     * Constructs a new {@link DatabaseConfiguration}
     *
     * @param address  the address of the database
     * @param username the username to connect to the database
     * @param password the password to connect to the database
     */
    public DatabaseConfiguration(final String address, final String username, final String password) {
        this.address = address;
        this.username = username;
        this.password = password;
    }
}
