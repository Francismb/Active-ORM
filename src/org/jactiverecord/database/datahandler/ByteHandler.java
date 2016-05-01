package org.jactiverecord.database.datahandler;

import org.jactiverecord.exceptions.DataHandleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class ByteHandler implements Handler<Byte> {

    @Override
    public Byte get(final int index, final ResultSet resultSet) {
        try {
            return resultSet.getByte(index);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Byte");
        }
    }

    @Override
    public void set(final int index, final Byte value, final PreparedStatement statement) {
        try {
            statement.setByte(index, value);
        } catch (SQLException e) {
            throw new DataHandleException(index, "Byte");
        }
    }

}
