package com.persoanltoy.backend.domains.order.domain.entity.value;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class Receiver {

    @NotBlank
    private String receiverName;

    @NotBlank
    private String receiverPhone;

}
