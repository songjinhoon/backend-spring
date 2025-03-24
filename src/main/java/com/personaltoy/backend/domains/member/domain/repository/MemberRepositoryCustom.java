package com.personaltoy.backend.domains.member.domain.repository;

import com.personaltoy.backend.domains.member.dto.request.MemberQueryDto;
import com.personaltoy.backend.domains.member.dto.response.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

    Page<MemberDto> query(Pageable pageable, MemberQueryDto memberQueryDto);

}
