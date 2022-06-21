package com.persoanltoy.backend.domain.todo.repository;

import com.persoanltoy.backend.domain.todo.entity.Todo;

import java.util.List;

public interface TodoRepositoryCustom {

    List<Todo> findByUsrIdToDetail(String usrId);

}
