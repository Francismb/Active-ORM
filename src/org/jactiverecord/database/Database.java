package org.jactiverecord.database;

import org.jactiverecord.database.configuration.DatabaseConfiguration;
import org.jactiverecord.database.configuration.SQLiteDatabaseConfiguration;
import org.jactiverecord.database.sql.SQLProducer;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * The {@link Database} object has the ability to execute
 * prepared statements on the database, it also contains the
 * database configuration and the connection to the database.
 */
public abstract class Database {

    /**
     * The connection to the database if there is one.
     */
    protected final Connection connection;

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
     * The static database instance.
     */
    private static Database instance;

    /**
     * Constructs a new {@link Database}.
     * Connects to the database when the object is constructed.
     *
     * @param configuration the database configuration.
     * @param sql the {@link SQLProducer}.
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
        connection = connect();
    }

    /**
     * Loads a {@link Database} from YAML.
     *
     * @param file the location of the file.
     * @return The constructed {@link Database}
     */
    public static Database fromYaml(final String file) {
        return fromYaml(new File(file));
    }

    /**
     * Loads a {@link Database} from YAML.
     *
     * @param file the yaml file.
     * @return The constructed {@link Database}
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

                        // Create the configuration and set the database instance
                        final SQLiteDatabaseConfiguration configuration = new SQLiteDatabaseConfiguration(map.get("address"));
                        Database.instance = new SQLiteDatabase(configuration);
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
     * @return the {@link Connection} that was created or null
     * if it failed to create a connection.
     */
    public abstract Connection connect();

    /**
     * Disconnects to the database.
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
     * @param sql the prepared statement sql.
     * @param parameters the parameters to prepare.
     * @return The result of the query or null if the query failed.
     */
    public ResultSet query(final String sql, final Object[] parameters) {
        final PreparedStatement statement = prepare(sql, parameters);
        if (statement != null) {
            try {
                return statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    System.out.println("Transaction has been rolled back");
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Executes a update query on the database(UPDATE, DELETE, INSERT) or
     * another query that does not return anything.
     * @param sql the prepared statement sql.
     * @param parameters the parameters to prepare.
     * @return the number of rows affected in the database.
     */
    public int execute(final String sql, final Object[] parameters) {
        final PreparedStatement statement = prepare(sql, parameters);
        if (statement != null) {
            try {
                return statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    System.out.println("Transaction has been rolled back");
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * Prepares a {@link PreparedStatement} with parameters.
     * @param sql the prepared statement sql.
     * @param parameters the parameters to prepare into the {@link PreparedStatement}.
     * @return a {@link PreparedStatement}.
     */
    public PreparedStatement prepare(final String sql, final Object[] parameters) {
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            if (sql.indexOf('?') == -1 || parameters.length == 0) {
                return statement;
            }
            for (int i = 0; i < parameters.length; i++) {
                final Object value = parameters[i];
                if (value instanceof String) {
                    statement.setString(i, (String) value);
                } else if (value instanceof Integer) {
                    statement.setInt(i, (Integer) value);
                } else if (value instanceof Float) {
                    statement.setFloat(i, (Float) value);
                } else if (value instanceof Short) {
                    statement.setShort(i, (Short) value);
                } else if (value instanceof Byte) {
                    statement.setByte(i, (Byte) value);
                }
            }
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
