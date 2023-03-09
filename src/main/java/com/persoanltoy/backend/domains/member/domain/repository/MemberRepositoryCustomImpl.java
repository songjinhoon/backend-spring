package com.persoanltoy.backend.domains.member.domain.repository;

import com.persoanltoy.backend.domains.common.CustomQueryDsl;
import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.dto.request.MemberQueryDto;
import com.persoanltoy.backend.domains.member.dto.response.MemberDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
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

import static com.persoanltoy.backend.domains.member.domain.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositoryCustomImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MemberDto> query(Pageable pageable, MemberQueryDto memberQueryDto) {
        List<OrderSpecifier<?>> orders = getOrders(pageable);
        QueryResults<Member> result = jpaQueryFactory.selectFrom(member)
                .where(
                        usernameContains(memberQueryDto.getUsername()),
                        nickNameContains(memberQueryDto.getNickName())
                )
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(MemberDto.of(result.getResults()), pageable, result.getTotal());
    }

    private BooleanExpression usernameContains(String username) {
        return hasText(username) ? member.username.contains(username) : null;
    }

    private BooleanExpression nickNameContains(String nickName) {
        return hasText(nickName) ? member.nickName.contains(nickName) : null;
    }

    private List<OrderSpecifier<?>> getOrders(Pageable pageable) {
        return pageable.getSort().isEmpty() ? new ArrayList<>() : pageable.getSort()
                .stream()
                .map(order -> CustomQueryDsl.getSortedColumn(order.getDirection().isAscending() ? Order.ASC : Order.DESC, member, order.getProperty()))
                .collect(Collectors.toList());
    }

}
