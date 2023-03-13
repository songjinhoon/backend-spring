package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.config.security.custom.CustomUserDetails;
import com.persoanltoy.backend.domains.order.domain.entity.Order;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderCreateDto;
import com.persoanltoy.backend.domains.order.service.OrderCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderCreateController {

    private final OrderCreateService orderCreateService;

    private final OrderValidator orderValidator;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid OrderCreateDto orderCreateDto, Errors errors, Authentication authentication) {
        if (orderValidator.validate(orderCreateDto, errors)) {
            return ResponseEntity.badRequest().body(errors);
        }
        Order order = orderCreateService.create(orderCreateDto, (CustomUserDetails) authentication.getPrincipal());

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(OrderCreateController.class).slash(order.getId());
        OrderResource orderResource = new OrderResource(order);
        orderResource.add(Link.of("/docs/index.html#resources-order-create", "profile"));
        orderResource.add(linkTo(OrderCreateController.class).withRel("query"));
        orderResource.add(webMvcLinkBuilder.withRel("update"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(orderResource);
    }

}
