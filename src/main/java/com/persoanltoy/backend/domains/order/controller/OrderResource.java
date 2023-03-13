package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.domains.order.domain.entity.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class OrderResource extends EntityModel<Order> {

    public OrderResource(Order order, Link... links) {
        super(order, Arrays.asList(links));
        add(linkTo(OrderCreateController.class).slash(order.getId()).withSelfRel());
    }

}
