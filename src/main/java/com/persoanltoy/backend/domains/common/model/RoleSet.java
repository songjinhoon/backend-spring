package com.persoanltoy.backend.domains.common.model;

import com.persoanltoy.backend.domains.member.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@AllArgsConstructor
public class RoleSet {

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

}
