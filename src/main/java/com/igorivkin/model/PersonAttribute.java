package com.igorivkin.model;

import com.igorivkin.common.AttributeType;
import lombok.*;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Entity;
import org.javers.core.metamodel.annotation.Id;

import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonAttribute {

    @DiffIgnore
    private Long attributeId;

    @Id
    @DiffIgnore
    private AttributeType id;

    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonAttribute that = (PersonAttribute) o;
        return id == that.id && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
