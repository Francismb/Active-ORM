package org.activerecord;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class GeneralTest {

    @Test
    public void test() {
        final UserModel model = new UserModel();
        model.name = "tom";
        model.password = "This is teh super secret password";
        model.save();
    }

}
