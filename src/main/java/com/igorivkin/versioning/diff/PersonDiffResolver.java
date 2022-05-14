package com.igorivkin.versioning.diff;

import com.igorivkin.common.HistoryEventType;
import com.igorivkin.model.Person;
import com.igorivkin.model.history.HistoryItem;
import org.javers.core.diff.changetype.InitialValueChange;
import org.javers.core.diff.changetype.ValueChange;

public class PersonDiffResolver extends DiffResolver {

    @Override
    public Class<?> getAffectedClass() {
        return Person.class;
    }

    @Override
    protected HistoryItem processInitialValueChange(InitialValueChange initialValueChange, Object affectedObject) {
        String propertyName = initialValueChange.getPropertyName();
        return HistoryItem.builder()
                .affectedClass(getAffectedClass())
                .eventType(HistoryEventType.ADD)
                .objectId(propertyName)
                .fieldName(propertyName)
                .newValue(initialValueChange.getRight())
                .build();
    }

    @Override
    protected HistoryItem processValueChange(ValueChange valueChange, Object affectedObject) {
        String propertyName = valueChange.getPropertyName();
        return HistoryItem.builder()
                .affectedClass(getAffectedClass())
                .eventType(HistoryEventType.UPDATE)
                .fieldName(propertyName)
                .oldValue(valueChange.getLeft())
                .newValue(valueChange.getRight())
                .build();
    }

}
