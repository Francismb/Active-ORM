package org.jactiverecord.database;

import java.sql.Connection;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public interface Database {

    /**
     * Establishes a {@link Connection} to the database
     * @return the {@link Connection} that was created or null
     * if it failed to create a connection.
     */
    public Connection connect();

    /**
     * Disconnects to the database
     * @return true if successfully disconnected else false.
     */
    public boolean disconnect();
}
