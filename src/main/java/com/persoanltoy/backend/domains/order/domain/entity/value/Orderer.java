package com.persoanltoy.backend.domains.order.domain.entity.value;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Orderer {

    private String ordererId;

    private String ordererName;

}
