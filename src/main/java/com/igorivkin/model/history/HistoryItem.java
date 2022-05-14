package com.igorivkin.model.history;

import com.igorivkin.common.HistoryEventType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryItem {

    private Class<?> affectedClass;

    private HistoryEventType eventType;

    private String objectId;

    private String fieldName;

    private Object oldValue;

    private Object newValue;

    @Override
    public String toString() {
        return "HistoryItem{" +
                "affectedClass=" + affectedClass +
                ", eventType=" + eventType +
                ", objectId='" + objectId + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", oldValue=" + oldValue +
                ", newValue=" + newValue +
                '}';
    }
}
