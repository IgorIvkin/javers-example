package com.igorivkin;

import com.igorivkin.common.AttributeType;
import com.igorivkin.model.MultilangDict;
import com.igorivkin.model.Person;
import com.igorivkin.model.PersonAttribute;
import com.igorivkin.versioning.DiffService;
import com.igorivkin.model.history.HistoryItem;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {
        DiffService diffService = new DiffService();
        List<HistoryItem> history = diffService.resolve(getPersonLeft(), getPersonRight());
        history.forEach(item -> log.info("{}", item));
    }

    private static Person getPersonLeft() {
        return Person.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .patronymicName(MultilangDict.builder()
                        .ru("Petrovitch")
                        .build())
                .attributes(List.of(
                        PersonAttribute.builder()
                                .attributeId(1L)
                                .id(AttributeType.EDUCATION)
                                .value("Public school")
                                .build()
                ))
                .build();
    }

    private static Person getPersonRight() {
        return Person.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Petrov")
                .patronymicName(MultilangDict.builder()
                        .ru("Ivanovitch")
                        .build())
                .attributes(List.of(
                        PersonAttribute.builder()
                                .attributeId(3L)
                                .id(AttributeType.EDUCATION)
                                .value("High school")
                                .build(),
                        PersonAttribute.builder()
                                .attributeId(2L)
                                .id(AttributeType.GENDER)
                                .value("Male")
                                .build()
                ))
                .build();
    }

}