package org.activeorm.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Francis on 5/10/2016.
 * Project Active-ORM.
 *
 * Resource is a object return from the query method in database.
 * It contains the results of the query and the ability to close them.
 */
public class Resource {

    /**
     * The results of the resource.
     */
    private final ResultSet result;

    /**
     * The statement that made the resource.
     */
    private final Statement statement;

    /**
     * Constructs a new {@link Resource}.
     *
     * @param statement The statement that made the result.
     * @param result    the result from the query.
     */
    public Resource(final Statement statement, final ResultSet result) {
        this.statement = statement;
        this.result = result;
    }

    /**
     * @return the result of this resource.
     */
    public ResultSet getResult() {
        return this.result;
    }

    /**
     * @return the statement of this resource
     */
    public Statement getStatment() {
        return this.statement;
    }

    /**
     * Releases all of the resources in this object.
     *
     * @return true if it releases without error.
     */
    public boolean release() {
        try {
            result.close();
            statement.close();
            return true;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
