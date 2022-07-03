package com.persoanltoy.backend.domain.auth.entity;

import com.persoanltoy.backend.domain.auth.dto.AuthCreateDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity @Getter
public class Auth {

    @Id
    @Column(name = "auth_id", length = 50)
    private String id;

    public static Auth create(AuthCreateDto authCreateDto) {
        return Auth.builder()
                .id(authCreateDto.getId())
                .build();
    }

}
