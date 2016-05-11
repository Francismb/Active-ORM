package org.activeorm.database.datahandler;

import org.activeorm.exceptions.DataHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Francis on 1/05/16.
 * Project Active-ORM.
 */
public class FloatHandler implements Handler<Float> {

    @Override
    public Float get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getFloat(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Float");
        }
    }

    @Override
    public void set(final int index, final Float value, final PreparedStatement statement) {
        try {
            statement.setFloat(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Float");
        }
    }

}

