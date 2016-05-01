package org.jactiverecord.database.datahandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public interface Handler<T> {

    public T get(final int index, final ResultSet resultSet);

    public void set(final int index, final T value, final PreparedStatement statement);

}
