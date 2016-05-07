package org.activeorm.database;

import org.activeorm.database.configuration.DatabaseConfiguration;
import org.activeorm.database.configuration.H2DatabaseConfiguration;
import org.activeorm.database.configuration.SQLiteDatabaseConfiguration;
import org.activeorm.database.datahandler.BooleanHandler;
import org.activeorm.database.datahandler.ByteHandler;
import org.activeorm.database.datahandler.DateHandler;
import org.activeorm.database.datahandler.DoubleHandler;
import org.activeorm.database.datahandler.FloatHandler;
import org.activeorm.database.datahandler.Handler;
import org.activeorm.database.datahandler.IntegerHandler;
import org.activeorm.database.datahandler.LongHandler;
import org.activeorm.database.datahandler.ShortHandler;
import org.activeorm.database.datahandler.StringHandler;
import org.activeorm.database.datahandler.TimeHandler;
import org.activeorm.database.datahandler.TimeStampHandler;
import org.activeorm.database.sql.SQLProducer;
import org.activeorm.exceptions.UnsupportedDataTypeException;
import org.activeorm.mapping.FieldMapping;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 * <p/>
 * The {@link Database} object has the ability to execute
 * prepared statements on the database, it also contains the
 * database configuration and the connection to the database.
 * <p/>
 * Must be constructed using the fromYaml static function.
 */
public abstract class Database {

    /**
     * The connection to the database if there is one.
     */
    protected Connection connection;

    /**
     * The database configuration object which contains
     * connection info.
     */
    protected final DatabaseConfiguration configuration;

    /**
     * The {@link SQLProducer} used to generate sql
     * for this database.
     */
    public final SQLProducer sql;

    /**
     * Data handlers used when converting class field values
     * to database data types.
     */
    public final Map<Class, Handler> handlers = new HashMap<Class, Handler>() {
        {
            put(Integer.class, new IntegerHandler());
            put(int.class, new IntegerHandler());
            put(String.class, new StringHandler());
            put(Boolean.class, new BooleanHandler());
            put(boolean.class, new BooleanHandler());
            put(Byte.class, new ByteHandler());
            put(byte.class, new ByteHandler());
            put(Date.class, new DateHandler());
            put(Double.class, new DoubleHandler());
            put(double.class, new DoubleHandler());
            put(Float.class, new FloatHandler());
            put(float.class, new FloatHandler());
            put(Long.class, new LongHandler());
            put(long.class, new LongHandler());
            put(Short.class, new ShortHandler());
            put(short.class, new ShortHandler());
            put(Time.class, new TimeHandler());
            put(Timestamp.class, new TimeStampHandler());
        }
    };

    /**
     * The static database instance.
     */
    private static Database instance;


    /**
     * Constructs a new {@link Database}.
     * Connects to the database when the object is constructed.
     *
     * @param configuration the database configuration.
     * @param sql           the {@link SQLProducer}.
     */
    protected Database(final DatabaseConfiguration configuration, final SQLProducer sql) {
        if (configuration == null) {
            throw new NullPointerException("A Configuration is required");
        }
        if (sql == null) {
            throw new NullPointerException("A SQLProducer is required");
        }
        this.configuration = configuration;
        this.sql = sql;
        this.connection = setupConnection();
    }

    /**
     * Attempts to setup the proper details for the connection. This will
     * set the transaction mode to explicit, requiring all transactions to
     * be manually committed.
     *
     * @throws java.lang.IllegalStateException if the result from
     * {@link Database#connect()} returned <code>null</code>.
     */
    private Connection setupConnection() {
        final Connection connection = connect();
        if(connection == null) {
            // not sure if this is the appropriate way to handle the
            // case where Database#connect returns null.
            throw new IllegalStateException("Creation of database connection failed");
        }

        try {
            connection.setAutoCommit(false);
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * Sets the static database instance to the one provided.
     *
     * @param database the {@link Database} to set the instance to.
     * @return The database passed in.
     */
    public static Database fromInstance(final Database database) {
        Database.instance = database;
        return database;
    }

    /**
     * Constructs a {@link Database} from YAML.
     *
     * @param file the location of the file.
     * @return The constructed {@link Database}.
     */
    public static Database fromYaml(final String file) {
        return fromYaml(new File(file));
    }

    /**
     * Constructs a {@link Database} from YAML.
     *
     * @param file the yaml file.
     * @return The constructed {@link Database}.
     */
    public static Database fromYaml(final File file) {
        // If the file does not exist throw an exception
        if (!file.exists()) {
            throw new IllegalArgumentException("Configuration File does not exist");
        }
        final Yaml yaml = new Yaml();
        try (final InputStream input = new FileInputStream(file)) {
            // Load the data into an object
            final Object data = yaml.load(input);
            if (data instanceof Map) {

                // Cast the data to a map
                final Map<String, String> map = (Map<String, String>) data;

                // If the map does not contain a specified connector throw an exception
                if (!map.containsKey("connector")) {
                    throw new RuntimeException("Configuration file does not contain a connector key");
                }
                switch (map.get("connector")) {
                    case "sqllite":
                        // If the map does not contain a location of the database then throw an exception
                        if (!map.containsKey("address")) {
                            throw new RuntimeException("Configuration file did not contain a address for the database");
                        }

                        // Get the data
                        final String address = map.get("address");

                        // Create the configuration and set the database instance
                        final SQLiteDatabaseConfiguration sqliteConfiguration = new SQLiteDatabaseConfiguration(address);
                        Database.instance = new SQLiteDatabase(sqliteConfiguration);
                        break;
                    case "h2":
                    case "h2database":
                        // Create the configuration and set the database instance
                        Database.instance = new H2Database();
                        break;
                    case "mysql":
                        break;
                    case "postgresql":
                        break;
                }

                // Return the database instance
                return Database.instance;
            } else {
                throw new RuntimeException("Configuration could not be cast to a map");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the {@link Database} instance.
     *
     * @return the {@link Database} instance.
     */
    public static Database getInstance() {
        if (instance == null) {
            throw new NullPointerException("Database has not been initialized yet");
        }
        return instance;
    }

    /**
     * Establishes a {@link Connection} to the database.
     *
     * @return the {@link Connection} that was created or null
     * if it failed to create a connection.
     */
    public abstract Connection connect();

    /**
     * Disconnects to the database.
     *
     * @return true if successfully disconnected else false.
     */
    public boolean disconnect() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Query's the database and returns data, if there
     * is an issue the query is rolled back.
     *
     * @param sql        the prepared statement sql.
     * @param parameters the parameters to prepare.
     * @return The result of the query or null if the query failed.
     */
    public synchronized ResultSet query(final String sql, final Object[] parameters) {
        final PreparedStatement statement = prepare(sql, parameters, false);
        if (statement != null) {
            try {
                return statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
                attemptRollback();
            }
        }
        return null;
    }

    /**
     * Executes a write query on the database(UPDATE, DELETE, INSERT) or
     * another query that does not return anything.
     *
     * @param sql        the prepared statement sql.
     * @param parameters the parameters to prepare.
     * @return the number of rows affected in the database.
     */
    public synchronized int execute(final String sql, final Object[] parameters) {
        final PreparedStatement statement = prepare(sql, parameters, false);
        if (statement != null) {
            try {
                final int result = statement.executeUpdate();
                statement.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
                attemptRollback();
            }
        }
        return -1;
    }

    /**
     * Executes a write query on the database(UPDATE, DELETE, INSERT) or
     * another query that does not return anything.
     *
     * @param sql        the prepared statement sql.
     * @param parameters the parameters to prepare.
     * @param primaryKey the primary key {@link FieldMapping} to set to the
     *                   generated keys.
     * @return the number of rows affected in the database.
     */
    public synchronized int execute(final String sql, final Object[] parameters, final FieldMapping primaryKey) {
        final PreparedStatement statement = prepare(sql, parameters, true);
        if (statement != null) {
            try {
                final int result = statement.executeUpdate();
                final ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    primaryKey.setValue(keys.getInt(1));
                }

                // be sure to unlock database tables
                keys.close();
                statement.close();
                connection.commit();

                return result;
            } catch (SQLException e) {
                e.printStackTrace();
                attemptRollback();
            }
        }
        return -1;
    }

    /**
     * Prepares a {@link PreparedStatement} with parameters.
     *
     * @param sql          the prepared statement sql.
     * @param parameters   the parameters to prepare into the {@link
     *                     PreparedStatement}.
     * @param generateKeys if true then statement will return keys.
     * @return a {@link PreparedStatement}.
     */
    private PreparedStatement prepare(final String sql, final Object[] parameters, boolean generateKeys) {
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, generateKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
            if (sql.indexOf('?') == -1 || parameters == null || parameters.length == 0) {
                return statement;
            }
            for (int i = 1; i < (parameters.length + 1); i++) {
                final Object value = parameters[i - 1];
                if (handlers.containsKey(value.getClass())) {
                    handlers.get(value.getClass()).set(i, value, statement);
                } else {
                    throw new UnsupportedDataTypeException(value.getClass().getName());
                }
            }
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Attempts to rollback the connection. This will do nothing if the
     * connection is closed or the connection is set to read-only mode.
     * Ideally, this should only be called in the event of an exception
     * during execution of an SQL statement. This will only work when the
     * transaction mode is not on auto-commit.
     */
    private void attemptRollback() {
        if (connection == null) {
            return;
        }
        try {
            // only roll back if it is possible to do so
            if (!connection.isClosed() && !connection.isReadOnly()) {
                // debug rollback print
                System.err.println("Transaction has been rolled back");
                connection.rollback();
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
