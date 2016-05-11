package org.activeorm.database.datahandler;

import org.activeorm.exceptions.DataHandleException;

import java.sql.*;

/**
 * Created by Francis on 1/05/16.
 * Project Active-ORM.
 */
public class TimeStampHandler implements Handler<Timestamp> {

    @Override
    public Timestamp get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getTimestamp(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "TimeStamp");
        }
    }

    @Override
    public void set(final int index, final Timestamp value, final PreparedStatement statement) {
        try {
            statement.setTimestamp(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "TimeStamp");
        }
    }
}

