package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.common.BaseTest;
import com.persoanltoy.backend.domains.member.dto.request.SignInDto;
import com.persoanltoy.backend.domains.member.dto.request.SignUpDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberCreateControllerTest extends BaseTest {

    @Test
    @DisplayName("sign-up success")
    void signUpSuccess() throws Exception {
        //given
        String url = "/member/sign-up";
        SignUpDto signUpDto = SignUpDto.builder()
                .username("hijinhoon@naver.com")
                .password("password")
                .nickName("hijinhoon")
                .build();

        //when - then
        super.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(signUpDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andDo(
                        document(
                                "sign-up",
                                requestFields(
                                        fieldWithPath("username").description("계정"),
                                        fieldWithPath("password").description("비밀번호"),
                                        fieldWithPath("nickName").description("닉네임")
                                )
                        )
                )
        ;
    }

    @MethodSource("paramForSignUpFail")
    @ParameterizedTest
    @DisplayName("sign-up-fail")
    void signUpFail(String username, String password, String nickName, ResultMatcher resultMatcher) throws Exception {
        //given
        String url = "/member/sign-up";
        SignUpDto signUpDto = SignUpDto.builder()
                .username(username)
                .password(password)
                .nickName(nickName)
                .build();

        //when - then
        super.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(signUpDto)))
                .andDo(print())
                .andExpect(resultMatcher)
        ;
    }

    private static Object[] paramForSignUpFail() {
        return new Object[]{
                new Object[]{"                 ", "password", "hijinhoon", status().isBadRequest()},
                new Object[]{"hijinhoon@naver.com", "123", "hijinhoon", status().isBadRequest()},
        };
    }

}
