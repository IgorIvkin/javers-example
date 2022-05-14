package com.igorivkin.model;

import lombok.*;
import org.javers.core.metamodel.annotation.Entity;
import org.javers.core.metamodel.annotation.Id;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private MultilangDict patronymicName;

    private List<PersonAttribute> attributes;

}
