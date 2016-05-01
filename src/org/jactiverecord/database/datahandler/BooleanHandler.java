package org.jactiverecord.database.datahandler;

import org.jactiverecord.exceptions.DataHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class BooleanHandler implements Handler<Boolean> {

    @Override
    public Boolean get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getBoolean(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Boolean");
        }
    }

    @Override
    public void set(final int index, Boolean value, final PreparedStatement statement) {
        try {
            statement.setBoolean(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Boolean");
        }
    }

}
