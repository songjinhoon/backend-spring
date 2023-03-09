package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.common.BaseTest;
import com.persoanltoy.backend.domains.member.dto.request.MemberUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberUpdateControllerTest extends BaseTest {

    @Test
    @DisplayName("update")
    void update() throws Exception {
        //given
        UUID generate = super.memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        String url = String.format("/member/%s", generate);
        MemberUpdateDto memberUpdateDto = MemberUpdateDto.builder()
                .nickName("changeNickName")
                .build();

        //when-then
        super.mockMvc.perform(put(url)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(memberUpdateDto))
                )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}