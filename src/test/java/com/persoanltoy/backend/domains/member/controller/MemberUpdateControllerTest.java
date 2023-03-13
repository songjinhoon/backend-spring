package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.common.BaseTest;
import com.persoanltoy.backend.domains.member.dto.request.MemberUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberUpdateControllerTest extends BaseTest {

    @Test
    @DisplayName("update")
    void update() throws Exception {
        //given
        String generate = super.memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        String url = String.format("/member/%s", generate);
        MemberUpdateDto memberUpdateDto = MemberUpdateDto.builder()
                .nickName("NickName~~")
                .build();

        //when-then
        super.mockMvc.perform(put(url)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(memberUpdateDto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "member-update",
                                requestFields(
                                        fieldWithPath("nickName").description("닉네임")
                                )
                        )
                )
        ;
    }

}