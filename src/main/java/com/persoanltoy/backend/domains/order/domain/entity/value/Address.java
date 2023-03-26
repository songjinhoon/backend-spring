package com.persoanltoy.backend.domains.order.domain.entity.value;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String address1;

    @NotBlank
    private String address2;

}
