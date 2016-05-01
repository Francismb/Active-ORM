package org.jactiverecord.database.datahandler;

import org.jactiverecord.exceptions.DataHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class IntegerHandler implements Handler<Integer> {

    @Override
    public Integer get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getInt(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Integer");
        }
    }

    @Override
    public void set(final int index, final Integer value, final PreparedStatement statement) {
        try {
            statement.setInt(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Integer");
        }
    }
}
