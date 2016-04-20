package org.activerecord.database.sql;

import jdk.nashorn.internal.objects.annotations.Where;
import org.activerecord.UserModel;
import org.jactiverecord.database.sql.DefaultSQLGenerator;
import org.jactiverecord.database.sql.expressions.OrderExpression;
import org.jactiverecord.database.sql.expressions.WhereExpression;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class GenerationTest {

    @Test
    public void testFullSelect() {
        final DefaultSQLGenerator generator = new DefaultSQLGenerator();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final WhereExpression[] conditions = new WhereExpression[] {
                new WhereExpression("condition_col_1", "="),
                new WhereExpression("condition_col_2", ">")
        };
        final OrderExpression[] orders = new OrderExpression[] {
                new OrderExpression("first_col", "ASC"),
                new OrderExpression("second_col", "DESC")
        };
        final String correctSQL = "SELECT `first_col`, `second_col`, `third_col` FROM `test_table` WHERE `condition_col_1` = ? AND `condition_col_2` > ? ORDER BY `first_col` ASC, `second_col` DESC LIMIT ?";
        assertEquals(generator.select("test_table", columns, conditions, orders, true), correctSQL);
    }

    @Test
    public void testPartialSelect() {
        final DefaultSQLGenerator generator = new DefaultSQLGenerator();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final WhereExpression[] conditions = new WhereExpression[] {
                new WhereExpression("condition_col_1", "="),
                new WhereExpression("condition_col_2", ">")
        };
        final String correctSQL = "SELECT `first_col`, `second_col`, `third_col` FROM `test_table` WHERE `condition_col_1` = ? AND `condition_col_2` > ? LIMIT ?";
        assertEquals(generator.select("test_table", columns, conditions, null, true), correctSQL);
    }

    @Test
    public void testMinimalSelect() {
        final DefaultSQLGenerator generator = new DefaultSQLGenerator();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final String correctSQL = "SELECT `first_col`, `second_col`, `third_col` FROM `test_table` LIMIT ?";
        assertEquals(generator.select("test_table", columns, null, null, true), correctSQL);
    }

    @Test
    public void testInsertSelect() {
        final DefaultSQLGenerator generator = new DefaultSQLGenerator();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final String correctSQL = "INSERT INTO test_table (`first_col`, `second_col`, `third_col`) VALUES (?, ?, ?)";
        assertEquals(generator.insert("test_table", columns), correctSQL);
    }

}
