package com.persoanltoy.backend.domain.usr.repository;

import com.persoanltoy.backend.domain.usr.dto.UsrSearchDto;
import com.persoanltoy.backend.domain.usr.dto.UsrSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsrRepositoryCustom {

    Page<UsrSimpleDto> find(UsrSearchDto usrSearchDto, Pageable pageable);

}
