package org.activeorm.database.datahandler;

import org.activeorm.exceptions.DataHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class ShortHandler implements Handler<Short> {

    @Override
    public Short get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getShort(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Short");
        }
    }

    @Override
    public void set(final int index, final Short value, final PreparedStatement statement) {
        try {
            statement.setShort(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Short");
        }
    }
}
