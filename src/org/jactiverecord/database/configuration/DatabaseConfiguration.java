package org.jactiverecord.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * A database configuration object containing information
 * on were the database is located
 */
public abstract class DatabaseConfiguration {

    /**
     * The ip address or location of the database
     */
    public final String url;

    /**
     * Constructs a new {@link DatabaseConfiguration}
     * @param url the url or location of the database
     */
    public DatabaseConfiguration(final String url) {
        this.url = url;
    }
}
