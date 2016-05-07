package org.activeorm.database;

import org.activeorm.database.configuration.SQLiteDatabaseConfiguration;
import org.activeorm.database.sql.DefaultSQLProducer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * A SQLite {@link Database} implementation.
 */
public class SQLiteDatabase extends Database {

    /**
     * Constructs a new {@link SQLiteDatabase}.
     *
     * @param configuration the database configuration object.
     */
    public SQLiteDatabase(final SQLiteDatabaseConfiguration configuration) {
        super(configuration, new DefaultSQLProducer());
    }

    public Connection connect() {
        try {
            // Load the SQLite driver class
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + super.configuration.address);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Refreshes the connection when dropping a table.
     */
    public synchronized int execute(final String sql, final Object[] parameters) {
        if (sql.toLowerCase().indexOf("drop") == 0) {
            disconnect();
            connect();
            return super.execute(sql, parameters);
        }
        return super.execute(sql, parameters);
    }
}
