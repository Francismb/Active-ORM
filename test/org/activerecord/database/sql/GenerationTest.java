package org.activerecord.database.sql;

import org.jactiverecord.database.sql.DefaultSQLProducer;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class GenerationTest {

    @Test
    public void testFullSelect() {
        final DefaultSQLProducer generator = new DefaultSQLProducer();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final String[] whereColumns = new String[]{"condition_col_1", "condition_col_2"};
        final String[] whereOperators = new String[]{"=", ">"};
        final String[] orderColumns = new String[]{"first_col", "second_col"};

        final String correctSQL = "SELECT `first_col`, `second_col`, `third_col` FROM `test_table` WHERE `condition_col_1` = ? AND `condition_col_2` > ? ORDER BY `first_col` ?, `second_col` ? LIMIT ?";
        assertEquals(generator.select("test_table", columns, whereColumns, whereOperators, orderColumns, true), correctSQL);
    }

    @Test
    public void testPartialSelect() {
        final DefaultSQLProducer generator = new DefaultSQLProducer();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final String[] whereColumns = new String[]{"condition_col_1", "condition_col_2"};
        final String[] whereOperators = new String[]{"=", ">"};
        final String correctSQL = "SELECT `first_col`, `second_col`, `third_col` FROM `test_table` WHERE `condition_col_1` = ? AND `condition_col_2` > ? LIMIT ?";
        assertEquals(generator.select("test_table", columns, whereColumns, whereOperators, null, true), correctSQL);
    }

    @Test
    public void testMinimalSelect() {
        final DefaultSQLProducer generator = new DefaultSQLProducer();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final String correctSQL = "SELECT `first_col`, `second_col`, `third_col` FROM `test_table` LIMIT ?";
        assertEquals(generator.select("test_table", columns, null, null, null, true), correctSQL);
    }

    @Test
    public void testInsert() {
        final DefaultSQLProducer generator = new DefaultSQLProducer();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final String correctSQL = "INSERT INTO `test_table` (`first_col`, `second_col`, `third_col`) VALUES (?, ?, ?)";
        assertEquals(generator.insert("test_table", columns), correctSQL);
    }

    @Test
    public void testUpdate() {
        final DefaultSQLProducer generator = new DefaultSQLProducer();
        final String[] columns = new String[] {
                "first_col", "second_col", "third_col"
        };
        final String[] whereColumns = new String[]{"first_col", "second_col"};
        final String[] whereOperators = new String[]{">=", "="};
        final String correctSQL = "UPDATE `test_table` SET `first_col` = ?, `second_col` = ?, `third_col` = ? WHERE `first_col` >= ? AND `second_col` = ?";
        assertEquals(generator.update("test_table", columns, whereColumns, whereOperators), correctSQL);
    }

    @Test
    public void testDelete() {
        final DefaultSQLProducer generator = new DefaultSQLProducer();
        final String[] whereColumns = new String[]{"first_col", "second_col"};
        final String[] whereOperators = new String[]{">=", "<="};
        final String correctSQL = "DELETE FROM `test_table` WHERE `first_col` >= ? AND `second_col` <= ?";
        assertEquals(generator.delete("test_table", whereColumns, whereOperators), correctSQL);
    }

}
