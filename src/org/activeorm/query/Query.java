package org.activeorm.query;

import org.activeorm.database.Database;
import org.activeorm.exceptions.UnsupportedDataTypeException;
import org.activeorm.mapping.ActiveRecord;
import org.activeorm.mapping.FieldMapping;
import org.activeorm.mapping.ObjectMapping;
import org.activeorm.mapping.StaticObjectMapping;
import org.activeorm.utility.Reflection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class Query<T extends ActiveRecord> {

    /**
     * The active record class that we are creating
     * a query for.
     */
    private final Class<T> activeRecord;

    /**
     * The {@link StaticObjectMapping} of the
     * {@link ActiveRecord} class.
     */
    private final StaticObjectMapping mapping;

    /**
     * A {@link List} of {@link WhereQuery}.
     */
    private final List<WhereQuery<T>> conditions;

    /**
     * A {@link List} of {@link OrderQuery}
     */
    private final List<OrderQuery<T>> orders;

    /**
     * The limit of results.
     */
    private int limit = -1;

    /**
     * Constructs a new {@link Query} from a
     * previously construcked {@link Query}.
     *
     * @param query the previous {@link Query}.
     */
    protected Query(final Query<T> query) {
        this.activeRecord = query.activeRecord;
        this.mapping = query.mapping;
        this.conditions = query.conditions;
        this.orders = query.orders;
    }

    /**
     * Constructs a new {@link Query}.
     *
     * @param activeRecord the active record to query.
     */
    protected Query(final Class<T> activeRecord) {
        this.activeRecord = activeRecord;
        this.mapping = new StaticObjectMapping(activeRecord);
        this.conditions = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    /**
     * Constructs a new {@link Query}.
     *
     * @param activeRecord the {@link ActiveRecord} to construct a query for.
     * @return a {@link Query} for the supplied {@link ActiveRecord}
     */
    public static <T extends ActiveRecord> Query<T> build(final Class<T> activeRecord) {
        return new Query<T>(activeRecord);
    }

    /**
     * Creates a {@link WhereQuery}.
     *
     * @param column the column to be used in the where condition.
     * @return a {@link WhereQuery}.
     */
    public WhereQuery<T> where(final String column) {
        final WhereQuery<T> condition = new WhereQuery<>(column, this);
        conditions.add(condition);
        return condition;
    }

    /**
     * Creats a {@link OrderQuery}.
     *
     * @param column the column to be used to order by.
     * @return a {@link OrderQuery}.
     */
    public OrderQuery order(final String column) {
        final OrderQuery<T> order = new OrderQuery<>(column, this);
        orders.add(order);
        return order;
    }

    /**
     * Limits the amount of results.
     *
     * @param limit the amount to limit the results by.
     * @return this {@link Query}.
     */
    public Query<T> limit(final int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Executes the query and returns the results.
     *
     * @return the results returned by the query
     */
    public List<T> results() {
        // Generate the where columns string array
        final String[] whereColumns = new String[conditions.size()];
        for (int i = 0; i < whereColumns.length; i++) {
            whereColumns[i] = conditions.get(i).column;
        }

        // Generate the where operator string array
        final String[] whereOperators = new String[conditions.size()];
        for (int i = 0; i < whereOperators.length; i++) {
            whereOperators[i] = conditions.get(i).operator;
        }

        // Generate the where value string array
        final Object[] whereValues = new Object[conditions.size()];
        for (int i = 0; i < whereValues.length; i++) {
            whereValues[i] = conditions.get(i).value;
        }

        // Generate the order columns
        final String[] orderColumns = new String[orders.size()];
        for (int i = 0; i < orderColumns.length; i++) {
            orderColumns[i] = orders.get(i).column;
        }

        // Generate the order directions
        final String[] orderDirections = new String[orders.size()];
        for (int i = 0; i < orderDirections.length; i++) {
            orderDirections[i] = orders.get(i).direction;
        }

        // Calculate the parameter length
        int parameterLength = whereValues.length;

        // If limit isn't -1 then add one to the parameter length
        if (limit != -1) {
            parameterLength += 1;
        }

        // Add the orderDirections and whereValues together
        final Object[] parameters = new Object[parameterLength];
        System.arraycopy(whereValues, 0, parameters, 0, whereValues.length);

        // If limit is not -1 then add the limit to the parameters
        if (limit != -1) {
            parameters[parameterLength - 1] = limit;
        }

        // Get the database
        final Database database = Database.getInstance();

        // Generate the sql
        final String sql = database.sql.select(mapping.table.name(), null, whereColumns, whereOperators, orderColumns, orderDirections, limit != -1);

        // Execute the query
        final ResultSet resultSet = database.query(sql, parameters);

        // The results converted into objects
        final List<T> results = new ArrayList<>();

        try {
            // Iterate through the result set
            while (resultSet.next()) {
                // Create a new object instance
                final T result = activeRecord.newInstance();

                // Get the object mapping from the instance
                final ObjectMapping mapping = (ObjectMapping) Reflection.getValue(result.getClass().getSuperclass(), "mapping", result);

                // Iterate through the field mappings and set the values
                for (final FieldMapping fieldMapping : mapping.mappings) {
                    // Get the index of the column in the results
                    final int index = resultSet.findColumn(fieldMapping.column.name());

                    // Get the type of the field
                    final Class type = fieldMapping.field.getType();

                    // Set the fields value
                    if (database.handlers.containsKey(type)) {
                        fieldMapping.setValue(database.handlers.get(type).get(index, resultSet));
                    } else {
                        throw new UnsupportedDataTypeException(type.getName());
                    }
                }

                // Add the results
                results.add(result);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the results resource
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    /**
     * Sets the limit of results to 1 and returns the
     * first result.
     *
     * @return the first result.
     */
    public T first() {
        // Set the limit to 1
        limit(1);

        // Generate the where columns string array
        final String[] whereColumns = new String[conditions.size()];
        for (int i = 0; i < whereColumns.length; i++) {
            whereColumns[i] = conditions.get(i).column;
        }

        // Generate the where operator string array
        final String[] whereOperators = new String[conditions.size()];
        for (int i = 0; i < whereOperators.length; i++) {
            whereOperators[i] = conditions.get(i).operator;
        }

        // Generate the where value string array
        final Object[] whereValues = new Object[conditions.size()];
        for (int i = 0; i < whereValues.length; i++) {
            whereValues[i] = conditions.get(i).value;
        }

        // Generate the order columns
        final String[] orderColumns = new String[orders.size()];
        for (int i = 0; i < orderColumns.length; i++) {
            orderColumns[i] = orders.get(i).column;
        }

        // Generate the order directions
        final String[] orderDirections = new String[orders.size()];
        for (int i = 0; i < orderDirections.length; i++) {
            orderDirections[i] = orders.get(i).direction;
        }

        // Calculate the parameter length
        int parameterLength = whereValues.length + 1;

        // Add the orderDirections and whereValues together
        final Object[] parameters = new Object[parameterLength];
        System.arraycopy(whereValues, 0, parameters, 0, whereValues.length);
        parameters[parameterLength - 1] = limit;

        // Get the database
        final Database database = Database.getInstance();

        // Generate the sql
        final String sql = database.sql.select(mapping.table.name(), null, whereColumns, whereOperators, orderColumns, orderDirections, limit != -1);

        // Execute the query
        final ResultSet resultSet = database.query(sql, parameters);

        try {
            // Iterate through the result set
            if (resultSet.next()) {
                // Create a new object instance
                final T result = activeRecord.newInstance();

                // Get the object mapping from the instance
                final ObjectMapping mapping = (ObjectMapping) Reflection.getValue(result.getClass().getSuperclass(), "mapping", result);

                // Iterate through the field mappings and set the values
                for (final FieldMapping fieldMapping : mapping.mappings) {
                    // Get the index of the column in the results
                    final int index = resultSet.findColumn(fieldMapping.column.name());

                    // Get the type of the field
                    final Class type = fieldMapping.field.getType();

                    // Set the fields value
                    if (database.handlers.containsKey(type)) {
                        fieldMapping.setValue(database.handlers.get(type).get(index, resultSet));
                    } else {
                        throw new UnsupportedDataTypeException(type.getName());
                    }
                }

                // Return the result
                return result;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the results resource
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Destroys all records that match the query.
     *
     * @return the number of records deleted.
     */
    public int destroy() {
        // Generate the where columns string array
        final String[] whereColumns = new String[conditions.size()];
        for (int i = 0; i < whereColumns.length; i++) {
            whereColumns[i] = conditions.get(i).column;
        }

        // Generate the where operator string array
        final String[] whereOperators = new String[conditions.size()];
        for (int i = 0; i < whereOperators.length; i++) {
            whereOperators[i] = conditions.get(i).operator;
        }

        // Generate the where value string array
        final Object[] whereValues = new Object[conditions.size()];
        for (int i = 0; i < whereValues.length; i++) {
            whereValues[i] = conditions.get(i).value;
        }

        // Get the database
        final Database database = Database.getInstance();

        // Generate the sql
        final String sql = database.sql.delete(mapping.table.name(), whereColumns, whereOperators);

        // Execute the query and return the amount of records deleted
        return database.execute(sql, whereValues);
    }

}
