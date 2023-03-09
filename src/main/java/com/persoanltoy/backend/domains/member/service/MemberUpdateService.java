package com.persoanltoy.backend.domains.member.service;

import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.domain.repository.MemberRepository;
import com.persoanltoy.backend.domains.member.dto.request.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberUpdateService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member update(UUID id, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        member.update(memberUpdateDto);
        return member;
    }

}
