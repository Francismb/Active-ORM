package org.activeorm.database.datahandler;

import org.activeorm.exceptions.DataHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Francis on 1/05/16.
 * Project Active-ORM.
 */
public class DoubleHandler implements Handler<Double> {

    @Override
    public Double get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getDouble(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Double");
        }
    }

    @Override
    public void set(final int index, final Double value, final PreparedStatement statement) {
        try {
            statement.setDouble(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Double");
        }
    }

}


