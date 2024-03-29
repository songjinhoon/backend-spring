package com.persoanltoy.backend.domains.member.service;

import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.domain.repository.MemberRepository;
import com.persoanltoy.backend.domains.member.dto.request.MemberQueryDto;
import com.persoanltoy.backend.domains.member.dto.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberFindService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member find(String id) {
        return memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public Page<MemberDto> query(Pageable pageable, MemberQueryDto memberQueryDto) {
        return memberRepository.query(pageable, memberQueryDto);
    }

}
