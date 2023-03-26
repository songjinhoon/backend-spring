package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.domains.order.domain.entity.Order;
import com.persoanltoy.backend.domains.order.domain.repository.OrderRepository;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderFindController {

    private final OrderRepository orderRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable String id) {
        Order order = orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        WebMvcLinkBuilder slash = linkTo(OrderFindController.class).slash(order.getId());
        OrderResource orderResource = new OrderResource(order);
        orderResource.add(Link.of("/docs/index.html#resources-order-find", "profile"));
        orderResource.add(linkTo(OrderFindController.class).withRel("query"));
        orderResource.add(slash.withRel("update"));

        return ResponseEntity.ok().body(orderResource);
    }

    @GetMapping
    public ResponseEntity<?> query(@PageableDefault(sort = "rgsDt", direction = Sort.Direction.DESC) Pageable pageable, OrderQueryDto orderQueryDto) {
        Page<Order> page = orderRepository.query(pageable, orderQueryDto);
        return ResponseEntity.ok().body(page);
    }

}

