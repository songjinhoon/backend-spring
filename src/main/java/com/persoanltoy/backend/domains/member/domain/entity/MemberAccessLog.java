package com.persoanltoy.backend.domains.member.domain.entity;

import com.persoanltoy.backend.domains.common.BaseLogEntity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.UUID;

@Embeddable
public class MemberAccessLog extends BaseLogEntity {

    @Embedded
    private UUID memberId;

    private String ip;

}
