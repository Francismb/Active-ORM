package org.jactiverecord.database.datahandler;

import org.jactiverecord.exceptions.DataHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class StringHandler implements Handler<String> {

    @Override
    public String get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getString(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "String");
        }
    }

    @Override
    public void set(final int index, final String value, final PreparedStatement statement) {
        try {
            statement.setString(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "String");
        }
    }
}
