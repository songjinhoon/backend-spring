package com.persoanltoy.backend.domains.member.service;

import com.persoanltoy.backend.domains.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberDeleteService {

    private final MemberRepository memberRepository;

    @Transactional
    public void delete(String id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    public void delete(List<String> ids) {
        memberRepository.deleteAllByIdInBatch(ids);
    }

}
