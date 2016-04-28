package org.jactiverecord;

import org.jactiverecord.mapping.FieldMapping;
import org.jactiverecord.mapping.ObjectMapping;
import org.jactiverecord.mapping.annotations.Column;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
public class ActiveRecord extends ObjectMapping {

    public boolean save() {
        final List<FieldMapping> changes = new ArrayList<FieldMapping>();
        for(final FieldMapping mapping : mappings) {
            if (mapping.hasBeenModified()) {
                changes.add(mapping);
            }
        }
        return true;
    }

}
