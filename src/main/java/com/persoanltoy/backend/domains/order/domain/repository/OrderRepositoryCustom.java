package com.persoanltoy.backend.domains.order.domain.repository;

import com.persoanltoy.backend.domains.order.domain.entity.Order;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {

    Page<Order> query(Pageable pageable, OrderQueryDto orderQueryDto);

}
