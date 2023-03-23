package com.persoanltoy.backend.domains.order.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderProduct {

    @NotBlank
    private String productId;

    @NotNull
    private Integer quantity;

}
