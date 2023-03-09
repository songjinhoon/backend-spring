package com.persoanltoy.backend.domains.member.domain.entity;

import com.persoanltoy.backend.domains.common.BaseTimeEntity;
import com.persoanltoy.backend.domains.member.domain.entity.role.Role;
import com.persoanltoy.backend.domains.member.domain.entity.role.RoleSet;
import com.persoanltoy.backend.domains.member.domain.entity.role.RoleSetConverter;
import com.persoanltoy.backend.domains.member.dto.request.MemberUpdateDto;
import com.persoanltoy.backend.domains.member.dto.request.SignUpDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id", columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String nickName;

    //    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "member_role", joinColumns = @JoinColumn(name = "member_id"))

    // @Convert 이슈인가? insert 시 update query가 날라간다.
    // Java에서는 객체의 equals를 override하지 않으면 레퍼런스 비교를 하기 때문에 값과 무관하게 false가 나올 수 있음.
    // 즉 트랜잭션이 끝나는 시점에 멤버 엔티티에 변경이 있다면 업데이트가 발생하는데, RoleSet객체의 Equalse를 오버라이드하지 않았기 떄문에 레퍼런스 비교로 더티 채킹이 발생하는 거같음
    @Column(name = "roles")
    @Convert(converter = RoleSetConverter.class)
    private RoleSet roleSet;

    public static Member create(SignUpDto signUpDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickName(signUpDto.getNickName())
                .roleSet(new RoleSet(Set.of(Role.ROLE_USER)))
                .build();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleSet.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }

    public void update(MemberUpdateDto memberUpdateDto) {
        this.nickName = memberUpdateDto.getNickName();
    }
}

