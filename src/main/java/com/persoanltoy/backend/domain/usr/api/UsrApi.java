package com.persoanltoy.backend.domain.usr.api;

import com.persoanltoy.backend.common.response.ResponseDto;
import com.persoanltoy.backend.common.response.ResponseMessage;
import com.persoanltoy.backend.domain.usr.service.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Api(tags = {"Usr"})
@RequiredArgsConstructor
@RequestMapping("/usr")
@RestController
public class UsrApi {

    private final UsrService usrService;

    @ApiOperation(value = "아이디 중복 체크", notes = "회원가입시 아이디 중복체크")
    @GetMapping("/idDuplicationCheck/{id}")
    public ResponseEntity<ResponseDto<?>> get(@PathVariable String id) {
        usrService.idDuplicationCheck(id);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .code(ResponseMessage.ACCOUNT_AVAILABLE.getCode())
                .message(ResponseMessage.ACCOUNT_AVAILABLE.getValue())
                .build());
    }

    @GetMapping(value = "/search1")
    public ResponseEntity<ResponseDto<?>> search1() {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(usrService.getMyUserWithAuthorities()))
                .build());
    }

    @GetMapping(value = "/search2/{nm}")
    public ResponseEntity<ResponseDto<?>> search2(@PathVariable String nm) {
        return ResponseEntity.ok().body(ResponseDto.builder()
                .data(Collections.singletonList(usrService.getUserWithAuthorities(nm)))
                .build());
    }

}
