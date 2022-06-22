package com.persoanltoy.backend.domain.usr.entity;

import com.persoanltoy.backend.domain.BaseEntity;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.usr.dto.UsrInsertDto;
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

    private String pwd;

    private String nm;

    @Builder.Default
    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();

    public static Usr empty() {
        return Usr.builder().build();
    }

    public static Usr create(UsrInsertDto usrInsertDto) {
        return Usr.builder()
                .id(usrInsertDto.getId())
                .pwd(usrInsertDto.getPwd())
                .nm(usrInsertDto.getNm())
                .build();
    }

}
