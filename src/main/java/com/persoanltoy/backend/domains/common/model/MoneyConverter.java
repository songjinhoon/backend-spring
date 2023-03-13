package com.persoanltoy.backend.domains.common.model;

import com.persoanltoy.backend.domains.common.model.Money;

import javax.persistence.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public Money convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : new Money(dbData);
    }

}
