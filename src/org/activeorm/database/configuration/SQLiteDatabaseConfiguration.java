package org.activeorm.database.configuration;

/**
 * Created by Francis on 9/04/16.
 * Project Active-ORM.
 *
 * A SQLite {@link DatabaseConfiguration} implementation.
 */
public class SQLiteDatabaseConfiguration extends DatabaseConfiguration {

    /**
     * Constructs a new {@link SQLiteDatabaseConfiguration}.
     *
     * @param address the address of the SQLLite database
     */
    public SQLiteDatabaseConfiguration(final String address) {
        super(null, address, null, null);
    }

}
