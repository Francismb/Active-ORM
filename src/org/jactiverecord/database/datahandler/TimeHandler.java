package org.jactiverecord.database.datahandler;

import org.jactiverecord.exceptions.DataHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class TimeHandler implements Handler<Time> {

    @Override
    public Time get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getTime(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Time");
        }
    }

    @Override
    public void set(final int index, final Time value, final PreparedStatement statement) {
        try {
            statement.setTime(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Time");
        }
    }
}

