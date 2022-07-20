package com.persoanltoy.backend.domain.usr.repository;

import com.persoanltoy.backend.domain.usr.dto.UsrSearchDto;
import com.persoanltoy.backend.domain.usr.dto.UsrSimpleDto;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.persoanltoy.backend.domain.usr.entity.QUsr.usr;
import static org.springframework.util.StringUtils.hasText;

public class UsrRepositoryCustomImpl implements UsrRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public UsrRepositoryCustomImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UsrSimpleDto> find(UsrSearchDto usrSearchDto, Pageable pageable) {
        QueryResults<Usr> result = jpaQueryFactory
                .selectFrom(usr)
                .where(nmEq(usrSearchDto.getNm()), idEq(usrSearchDto.getNm()))
                .orderBy(usr.rgsDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Usr> results = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(UsrSimpleDto.of(results), pageable, total);
    }

    private BooleanExpression nmEq(String nm) {
        return hasText(nm) ? usr.nm.eq(nm) : null;
    }

    private BooleanExpression idEq(String id) {
        return hasText(id) ? usr.id.eq(id) : null;
    }

}
