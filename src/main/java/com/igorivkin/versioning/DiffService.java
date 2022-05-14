package com.igorivkin.versioning;

import com.igorivkin.model.Person;
import com.igorivkin.model.PersonAttribute;
import com.igorivkin.versioning.diff.DiffResolver;
import com.igorivkin.versioning.diff.PersonAttributeDiffResolver;
import com.igorivkin.versioning.diff.PersonDiffResolver;
import com.igorivkin.model.history.HistoryItem;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DiffService {

    private final Javers javers;

    private final Map<Class<?>, DiffResolver> diffResolvers = new HashMap<>();

    public DiffService() {
        javers = JaversBuilder.javers()
                .withListCompareAlgorithm(ListCompareAlgorithm.AS_SET)
                .build();

        diffResolvers.put(Person.class, new PersonDiffResolver());
        diffResolvers.put(PersonAttribute.class, new PersonAttributeDiffResolver());
    }

    public List<HistoryItem> resolve(Object left, Object right) {
        List<HistoryItem> history = new ArrayList<>();
        Diff diff = javers.compare(left, right);
        Changes changes = diff.getChanges();
        for (Change change : changes) {
            Object affectedObject = change.getAffectedObject().orElse(null);
            if (affectedObject != null) {
                Class<?> affectedClass = affectedObject.getClass();
                DiffResolver diffResolver = diffResolvers.get(affectedClass);
                if (diffResolver == null) {
                    throw new IllegalStateException("Has no diff resolver for type: " + affectedClass);
                } else {
                    HistoryItem historyItem = diffResolver.processChange(change, affectedObject);
                    if (historyItem != null) {
                        history.add(historyItem);
                    }
                }
            }
        }
        return history;
    }
}
