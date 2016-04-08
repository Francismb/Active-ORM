package org.jactiverecord.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * A SQLite database configuration object
 */
public class SQLiteDatabaseConfiguration extends DatabaseConfiguration {

    /**
     * Constructs a new {@link SQLiteDatabaseConfiguration}
     * @param location the location of the SQLLite database
     */
    public SQLiteDatabaseConfiguration(final String location) {
        super(location, null, null);
    }

}
