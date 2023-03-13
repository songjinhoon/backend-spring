package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.domains.order.dto.reqeust.OrderCreateDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class OrderValidator {

    public boolean validate(OrderCreateDto orderCreateDto, Errors errors) {
        if (errors.hasErrors()) {
            return true;
        }
        return errors.hasErrors();
    }

}
