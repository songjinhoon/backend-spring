package com.persoanltoy.backend.domain.member.repository;

import com.persoanltoy.backend.domain.member.dto.UsrSearchDto;
import com.persoanltoy.backend.domain.member.dto.UsrSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsrRepositoryCustom {

    Page<UsrSimpleDto> find(UsrSearchDto usrSearchDto, Pageable pageable);

}
