package com.igorivkin.versioning.diff;

import com.igorivkin.common.HistoryEventType;
import com.igorivkin.model.PersonAttribute;
import com.igorivkin.model.history.HistoryItem;
import org.javers.core.diff.changetype.InitialValueChange;
import org.javers.core.diff.changetype.ValueChange;

public class PersonAttributeDiffResolver extends DiffResolver {

    @Override
    public Class<?> getAffectedClass() {
        return PersonAttribute.class;
    }

    @Override
    protected HistoryItem processInitialValueChange(InitialValueChange initialValueChange, Object affectedObject) {
        PersonAttribute personAttribute = (PersonAttribute) affectedObject;
        String propertyName = initialValueChange.getPropertyName();
        return HistoryItem.builder()
                .affectedClass(getAffectedClass())
                .eventType(HistoryEventType.ADD)
                .objectId(personAttribute.getId().toString())
                .fieldName(propertyName)
                .newValue(initialValueChange.getRight())
                .build();
    }

    @Override
    protected HistoryItem processValueChange(ValueChange valueChange, Object affectedObject) {
        PersonAttribute personAttribute = (PersonAttribute) affectedObject;
        String propertyName = valueChange.getPropertyName();
        return HistoryItem.builder()
                .affectedClass(getAffectedClass())
                .eventType(HistoryEventType.UPDATE)
                .objectId(personAttribute.getId().toString())
                .fieldName(propertyName)
                .oldValue(valueChange.getLeft())
                .newValue(valueChange.getRight())
                .build();
    }

}
