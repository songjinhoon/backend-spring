package com.persoanltoy.backend.domain.todo.repository;

import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.persoanltoy.backend.domain.todo.entity.QTodo.todo;
import static com.persoanltoy.backend.domain.usr.entity.QUsr.usr;

public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public TodoRepositoryCustomImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Todo> findByUsrIdToDetail(String usrId) {
        return jpaQueryFactory.selectFrom(todo)
                .innerJoin(todo.usr, usr).fetchJoin()
                .where(usr.id.eq(usrId))
                .fetch();
    }
}
