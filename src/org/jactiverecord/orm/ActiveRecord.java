package org.jactiverecord.orm;

import org.jactiverecord.orm.mapping.FieldMapping;
import org.jactiverecord.orm.mapping.ObjectMapping;
import org.jactiverecord.query.Query;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class ActiveRecord extends ObjectMapping {

    public boolean save() {
        for(final FieldMapping mapping : mappings) {
            if (mapping.hasBeenModified()) {
                // Update the record
                System.out.println(mapping.getValue());
            }
        }
        return true;
    }

}
