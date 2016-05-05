package org.activeorm.database.datahandler;

import org.activeorm.exceptions.DataHandleException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class DateHandler implements Handler<Date> {

    @Override
    public Date get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getDate(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Date");
        }
    }

    @Override
    public void set(final int index, final Date value, final PreparedStatement statement) {
        try {
            statement.setDate(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Date");
        }
    }

}

