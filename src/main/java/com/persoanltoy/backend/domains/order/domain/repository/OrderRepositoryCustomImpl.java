package com.persoanltoy.backend.domains.order.domain.repository;

import com.persoanltoy.backend.domains.common.CustomQueryDsl;
import com.persoanltoy.backend.domains.order.domain.entity.Order;
import com.persoanltoy.backend.domains.order.domain.entity.QOrder;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderQueryDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.persoanltoy.backend.domains.order.domain.entity.QOrder.order;
import static org.springframework.util.StringUtils.hasText;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepositoryCustomImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Order> query(Pageable pageable, OrderQueryDto orderQueryDto) {
        List<OrderSpecifier<?>> orders = getOrders(pageable);
        QueryResults<Order> orderQueryResults = jpaQueryFactory.selectFrom(order)
                .where(
                        ordererNameContains(orderQueryDto.getOrdererName()),
                        receiverNameContains(orderQueryDto.getReceiverName())
                )
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(orderQueryResults.getResults(), pageable, orderQueryResults.getTotal());
    }

    private BooleanExpression ordererNameContains(String ordererName) {
        return hasText(ordererName) ? order.orderer.ordererName.contains(ordererName) : null;
    }

    private BooleanExpression receiverNameContains(String receiverName) {
        return hasText(receiverName) ? order.shippingInfo.receiver.receiverName.contains(receiverName) : null;
    }

    private List<OrderSpecifier<?>> getOrders(Pageable pageable) {
        return pageable.getSort().isEmpty() ? new ArrayList<>() : pageable.getSort()
                .stream()
                .map(order -> CustomQueryDsl.getSortedColumn(order.getDirection().isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC, QOrder.order, order.getProperty()))
                .collect(Collectors.toList());
    }

}
