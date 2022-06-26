package com.persoanltoy.backend.config.security;

import com.persoanltoy.backend.domain.usr.entity.Usr;
import com.persoanltoy.backend.domain.usr.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsrRepository usrRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return usrRepository.findOneWithAuthsById(username)
                .map(usr -> createUser(username, usr))
                .orElseThrow(IllegalArgumentException::new);
    }

    private User createUser(String nm, Usr usr) {
        List<GrantedAuthority> grantedAuthorities = usr.getAuths().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getId()))
                .collect(Collectors.toList());
        return new User(usr.getNm(), usr.getPwd(), grantedAuthorities);
    }

}