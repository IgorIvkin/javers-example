package com.igorivkin.versioning.diff;

import com.igorivkin.model.history.HistoryItem;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.InitialValueChange;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.ListChange;

@Slf4j
public abstract class DiffResolver {

    public abstract Class<?> getAffectedClass();

    protected abstract HistoryItem processInitialValueChange(InitialValueChange initialValueChange,
                                                             Object affectedObject);

    protected abstract HistoryItem processValueChange(ValueChange valueChange, Object affectedObject);

    public HistoryItem processChange(Change change, Object affectedObject) {
        if (change instanceof InitialValueChange) {
            InitialValueChange initialValueChange = (InitialValueChange) change;
            return processInitialValueChange(initialValueChange, affectedObject);
        } else if (change instanceof NewObject) {
            log.debug("NewObject change type is not processing currently");
            return null;
        } else if (change instanceof ListChange) {
            log.debug("ListChange change type is not processing currently");
            return null;
        } else if (change instanceof ValueChange) {
            ValueChange valueChange = (ValueChange) change;
            return processValueChange(valueChange, affectedObject);
        } else {
            throw new IllegalStateException("Unknown change type:" + change.getClass());
        }
    }

}
