package com.persoanltoy.backend.config.security.custom;

import com.persoanltoy.backend.domains.member.domain.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final String memberId;

    private final String username;

    private final String password;

    private final String nickName;

    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Member member) {
        this.memberId = member.getId();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.nickName = member.getNickName();
        this.authorities = member.getAuthorities();

    }

    public CustomUserDetails(String memberId, String username, String password, String nickName, Collection<? extends GrantedAuthority> authorities) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public String getNickName() {
        return this.nickName;
    }

}
