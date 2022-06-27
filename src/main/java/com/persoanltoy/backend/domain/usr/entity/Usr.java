package com.persoanltoy.backend.domain.usr.entity;

import com.persoanltoy.backend.domain.BaseEntity;
import com.persoanltoy.backend.domain.auth.entity.Auth;
import com.persoanltoy.backend.domain.todo.entity.Todo;
import com.persoanltoy.backend.domain.usr.dto.UsrCreateDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity @Getter
public class Usr extends BaseEntity {

    @Id
    @Column(name = "usr_id")
    private String id;

    @ManyToMany
    @JoinTable(
            name="usr_auth",
            joinColumns = {@JoinColumn(name = "usr_id", referencedColumnName = "usr_id")},
            inverseJoinColumns = {@JoinColumn(name = "auth_id", referencedColumnName = "auth_id")})
    private Set<Auth> auths;

    private String pwd;

    @Column(unique = true)
    private String nm;

    @Builder.Default
    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();

    public static Usr create(UsrCreateDto usrCreateDto) {
        return Usr.builder()
                .id(usrCreateDto.getId())
                .pwd(usrCreateDto.getPwd())
                .nm(usrCreateDto.getNm())
                .build();
    }

    public static Usr empty() {
        return Usr.builder().build();
    }

}
