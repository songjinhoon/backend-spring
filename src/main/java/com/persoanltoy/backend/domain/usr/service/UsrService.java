package com.persoanltoy.backend.domain.usr.service;

import com.persoanltoy.backend.domain.usr.dto.UsrInsertDto;
import com.persoanltoy.backend.domain.usr.dto.UsrSimpleDto;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import com.persoanltoy.backend.domain.usr.repository.UsrRepository;
import com.persoanltoy.backend.exception.IdDuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsrService {

    private final UsrRepository usrRepository;

    public void idDuplicationCheck(String id) {
        Optional<Usr> optional = usrRepository.findById(id);
        if (optional.isPresent()) {
            throw new IdDuplicationException();
        }
    }

    public UsrSimpleDto save(UsrInsertDto usrInsertDto) {
        return UsrSimpleDto.of(usrRepository.save(Usr.create(usrInsertDto)));
    }

}
