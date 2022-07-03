package com.persoanltoy.backend.domain.usr.service;

import com.persoanltoy.backend.config.security.SecurityUtil;
import com.persoanltoy.backend.domain.auth.dto.SignUpDto;
import com.persoanltoy.backend.domain.auth.entity.Auth;
import com.persoanltoy.backend.domain.auth.repository.AuthRepository;
import com.persoanltoy.backend.domain.usr.dto.UsrDetailDto;
import com.persoanltoy.backend.domain.usr.dto.UsrSimpleDto;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import com.persoanltoy.backend.domain.usr.repository.UsrRepository;
import com.persoanltoy.backend.common.exception.IdDuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsrService {

    private final UsrRepository usrRepository;

    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public void idDuplicationCheck(String id) {
        Optional<Usr> optional = usrRepository.findById(id);
        if (optional.isPresent()) {
            throw new IdDuplicationException();
        }
    }

    @Transactional
    public UsrSimpleDto save(SignUpDto signUpDto) {
        final Auth auth = authRepository.findById("ROLE_USER").orElseThrow(IllegalArgumentException::new);
        signUpDto.setPwd(passwordEncoder.encode(signUpDto.getPwd()));
        return UsrSimpleDto.of(usrRepository.save(Usr.create(signUpDto, Collections.singleton(auth))));
    }

    @Transactional(readOnly = true)
    public UsrDetailDto getUserWithAuthorities(String nm) {
        final Usr usr = usrRepository.findOneWithAuthsByNm(nm).orElseThrow(IllegalArgumentException::new);
        return UsrDetailDto.of(usr);
    }

    @Transactional(readOnly = true)
    public UsrDetailDto getMyUserWithAuthorities() {
        final Usr usr = usrRepository.findOneWithAuthsByNm(SecurityUtil.getCurrentUsername().get()).orElseThrow(IllegalArgumentException::new);
        return UsrDetailDto.of(usr);
    }

}
