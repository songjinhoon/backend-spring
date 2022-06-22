package com.persoanltoy.backend.domain.usr.api;

import com.persoanltoy.backend.domain.ResponseDto;
import com.persoanltoy.backend.domain.ResponseMessage;
import com.persoanltoy.backend.domain.usr.dto.UsrInsertDto;
import com.persoanltoy.backend.domain.usr.dto.UsrSimpleDto;
import com.persoanltoy.backend.domain.usr.service.UsrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/usr")
@RestController
public class UsrApi {

    private final UsrService usrService;

    @GetMapping("/idDuplicationCheck/{id}")
    public ResponseEntity<ResponseDto<Map<String, String>>> idDuplicationCheck(@PathVariable String id) {
        usrService.idDuplicationCheck(id);
        return ResponseEntity.ok().body(ResponseDto.<Map<String, String>>builder()
                .data(Collections.singletonList(Map.of("message", ResponseMessage.ACCOUNT_DUPLICATION.getValue())))
                .build());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<UsrSimpleDto>> save(@Valid @RequestBody UsrInsertDto usrInsertDto) {
        final UsrSimpleDto usrSimpleDto = usrService.save(usrInsertDto);
        return ResponseEntity.ok().body(ResponseDto.<UsrSimpleDto>builder()
                .data(Collections.singletonList(usrSimpleDto))
                .build());
    }

}
