package com.persoanltoy.backend.domains.member.service;

import com.persoanltoy.backend.domains.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberDeleteService {

    private final MemberRepository memberRepository;

    @Transactional
    public void delete(UUID id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    public void delete(List<UUID> ids) {
        memberRepository.deleteAllByIdInBatch(ids);
    }

}
