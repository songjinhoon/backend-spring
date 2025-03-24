package com.personaltoy.backend.domains.order.infra;

import com.personaltoy.backend.domains.order.domain.entity.Order;
import com.personaltoy.backend.domains.order.dto.reqeust.OrderQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {

    Page<Order> query(Pageable pageable, OrderQueryDto orderQueryDto);

}
