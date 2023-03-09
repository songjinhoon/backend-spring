package com.persoanltoy.backend.domains.member.service;

import com.persoanltoy.backend.domains.member.dto.request.SignUpDto;
import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.domain.repository.MemberRepository;
import com.persoanltoy.backend.domains.member.dto.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberCreateService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDto save(SignUpDto signUpDto) {
        Member member = memberRepository.save(Member.create(signUpDto, passwordEncoder));
        return MemberDto.of(member);
    }

}
