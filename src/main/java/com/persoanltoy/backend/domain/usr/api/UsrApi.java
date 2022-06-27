package com.persoanltoy.backend.domain.usr.api;

import com.persoanltoy.backend.common.response.ResponseDto;
import com.persoanltoy.backend.common.response.ResponseMessage;
import com.persoanltoy.backend.domain.usr.dto.UsrCreateDto;
import com.persoanltoy.backend.domain.usr.service.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Usr"})
@RequiredArgsConstructor
@RequestMapping("/usr")
@RestController
public class UsrApi {

    private final UsrService usrService;

    @ApiOperation(value = "사용자 등록", notes = "회원가입")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<?>> post(@Valid @RequestBody UsrCreateDto usrCreateDto) {
        usrService.save(usrCreateDto);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .code(ResponseMessage.SUCCESS_CREATE.getCode())
                .message(ResponseMessage.SUCCESS_CREATE.getValue())
                .build());
    }

    @ApiOperation(value = "아이디 중복 체크", notes = "회원가입시 아이디 중복체크")
    @GetMapping("/idDuplicationCheck/{id}")
    public ResponseEntity<ResponseDto<?>> get(@PathVariable String id) {
        usrService.idDuplicationCheck(id);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .code(ResponseMessage.ACCOUNT_AVAILABLE.getCode())
                .message(ResponseMessage.ACCOUNT_AVAILABLE.getValue())
                .build());
    }

}
