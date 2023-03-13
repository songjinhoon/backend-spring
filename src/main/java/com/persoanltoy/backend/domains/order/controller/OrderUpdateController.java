package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.domains.order.dto.reqeust.OrderCancelDto;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderStateUpdateDto;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderUpdateDto;
import com.persoanltoy.backend.domains.order.service.OrderUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderUpdateController {

    private final OrderUpdateService orderUpdateService;

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid OrderUpdateDto orderUpdateDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        orderUpdateService.update(id, orderUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/state/{id}")
    public ResponseEntity<?> stateUpdate(@PathVariable String id, @RequestBody @Valid OrderStateUpdateDto orderUpdateDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        orderUpdateService.update(id, orderUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelUpdate(@PathVariable String id, @RequestBody @Valid OrderCancelDto orderCancelDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        orderUpdateService.cancel(id, orderCancelDto);
        return ResponseEntity.noContent().build();
    }

}
