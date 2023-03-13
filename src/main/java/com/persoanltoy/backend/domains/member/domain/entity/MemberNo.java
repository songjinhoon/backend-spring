package com.persoanltoy.backend.domains.member.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Embeddable
public class MemberNo implements Serializable {

    @Column(name = "member_id")
    private UUID id;

    protected MemberNo() {

    }

    public static MemberNo create() {
        MemberNo memberNo = new MemberNo();
        memberNo.id = UUID.randomUUID();
        return memberNo;
    }

    public static MemberNo of(UUID id) {
        MemberNo memberNo = new MemberNo();
        memberNo.id = id;
        return memberNo;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

}
