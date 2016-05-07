package org.activeorm.database;

import org.activeorm.database.configuration.H2DatabaseConfiguration;
import org.activeorm.database.sql.DefaultSQLProducer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Francis on 7/05/16.
 * Project Active-ORM.
 *
 * A H2Database {@link Database} implementation.
 */
public class H2Database extends Database {

    /**
     * Constructs a new {@link H2Database}.
     */
    public H2Database() {
        super(new H2DatabaseConfiguration(), new DefaultSQLProducer());
    }

    public Connection connect() {
        try {
            // Load the HSQLDB driver class
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:database", "sa", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
