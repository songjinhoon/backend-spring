package com.persoanltoy.backend.domains.member.domain.entity.role;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleSetConverter implements javax.persistence.AttributeConverter<RoleSet, String> {

    @Override
    public String convertToDatabaseColumn(RoleSet attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getRoles()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Override
    public RoleSet convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        String[] roles = dbData.split(",");
        Set<Role> collect = Arrays.stream(roles)
                .map(Role::valueOf)
                .collect(Collectors.toSet());
        return new RoleSet(collect);
    }

}
