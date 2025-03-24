package com.personaltoy.backend.domains.member.service;

import com.personaltoy.backend.domains.member.domain.entity.Member;
import com.personaltoy.backend.domains.member.domain.repository.MemberRepository;
import com.personaltoy.backend.domains.member.dto.request.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberUpdateService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member update(String id, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        member.update(memberUpdateDto);
        return member;
    }

}
