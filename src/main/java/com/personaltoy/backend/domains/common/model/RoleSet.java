package com.personaltoy.backend.domains.common.model;

import com.personaltoy.backend.domains.member.domain.entity.Role;
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
