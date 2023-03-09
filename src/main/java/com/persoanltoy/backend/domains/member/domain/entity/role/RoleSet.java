package com.persoanltoy.backend.domains.member.domain.entity.role;

import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
public class RoleSet {

    private Set<Role> roles;

    public RoleSet(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

}
