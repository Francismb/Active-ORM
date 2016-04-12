package org.jactiverecord;

import org.jactiverecord.mapping.FieldMapping;
import org.jactiverecord.mapping.ObjectMapping;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class ActiveRecord extends ObjectMapping {

    public boolean save() {
        for(final FieldMapping mapping : mappings) {
            if (mapping.hasBeenModified()) {
                System.out.println(mapping.getValue());
            }
        }
        return true;
    }

}
