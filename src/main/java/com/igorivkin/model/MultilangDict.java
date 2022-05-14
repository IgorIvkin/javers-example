package com.igorivkin.model;

import lombok.*;
import org.javers.core.metamodel.annotation.Value;

import java.util.Objects;

@Value
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultilangDict {

    private String en;

    private String ru;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultilangDict that = (MultilangDict) o;
        return Objects.equals(en, that.en) && Objects.equals(ru, that.ru);
    }

    @Override
    public int hashCode() {
        return Objects.hash(en, ru);
    }

    @Override
    public String toString() {
        return "MultilangDict{" +
                "en='" + en + '\'' +
                ", ru='" + ru + '\'' +
                '}';
    }
}
