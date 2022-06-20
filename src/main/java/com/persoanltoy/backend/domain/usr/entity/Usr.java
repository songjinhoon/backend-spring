package com.persoanltoy.backend.domain.usr.entity;

import com.persoanltoy.backend.domain.BaseEntity;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity @Getter
public class Usr extends BaseEntity {

    @Id
    @Column(name = "usr_id")
    private String id;

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();

    private String nm;

    public static Usr empty() {
        return Usr.builder().build();
    }

}
