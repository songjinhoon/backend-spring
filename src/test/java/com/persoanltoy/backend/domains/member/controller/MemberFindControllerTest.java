package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.common.BaseTest;
import com.persoanltoy.backend.domains.member.dto.request.MemberQueryDto;
import com.persoanltoy.backend.domains.member.dto.request.SignInDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberFindControllerTest extends BaseTest {

    @Test
    @DisplayName("sign-in success")
    void signInFail() throws Exception {
        //given
        super.memberDummyGenerate.generate();
        String url = "/member/sign-in";
        SignInDto signInDto = SignInDto.builder()
                .username("hijinhoon@naver.com")
                .password("password")
                .build();

        //when-then
        super.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(signInDto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "sign-in",
                                requestFields(
                                        fieldWithPath("username").description("계정"),
                                        fieldWithPath("password").description("비밀번호")
                                )
                        )
                )
        ;
    }

    @ParameterizedTest
    @MethodSource("paramForSignInFail")
    @DisplayName("sign-in fail")
    void signInFail(String username, String password, ResultMatcher resultMatcher) throws Exception {
        //given
        super.memberDummyGenerate.generate();
        String url = "/member/sign-in";
        SignInDto signInDto = SignInDto.builder()
                .username(username)
                .password(password)
                .build();

        //when-then
        super.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(signInDto))
                )
                .andDo(print())
                .andExpect(resultMatcher)
        ;
    }

    private static Object[] paramForSignInFail() {
        return new Object[]{
                new Object[]{"", "", status().isOk()}, // UserNameNotFoundException
                new Object[]{"hijinhoon@naver.com", "twkaiodjkaljc", status().isOk()}, // BadCredentialsException
        };
    }

    @Test
    @DisplayName("find")
    void find_success() throws Exception {
        //given
        UUID generate = super.memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        String url = String.format("/member/%s", generate);

        //when-then
        super.mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("member-find")
                )
        ;
    }

    @Test
    @DisplayName("query")
    void query() throws Exception {
        //given
        super.memberDummyGenerate.generate(100);
        UUID generate = super.memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        String url = "/member";

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("page", "1");
        parameters.add("size", "5");
        parameters.add("sort", "username,desc");
        parameters.add("sort", "nickName,asc"); // asc 는 생략 가능~
        parameters.add("username", "hijinhoon");
        parameters.add("nickName", "1");

        //when-then
        super.mockMvc.perform(get(url)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                        .params(parameters)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "member-query",
                                requestParameters(
                                        parameterWithName("page").description("페이지번호"),
                                        parameterWithName("size").description("페이지크기"),
                                        parameterWithName("sort").description("정렬"),
                                        parameterWithName("username").description("계정"),
                                        parameterWithName("nickName").description("닉네임")
                                )
                        )
                )
        ;
    }

    @Test
    @DisplayName("401 error")
    void find_fail() throws Exception {
        //given
        UUID generate = super.memberDummyGenerate.generate();
        String url = String.format("/member/%s", generate);

        //when-then
        super.mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @DisplayName("no data")
    void find_success_but_no_data() throws Exception {
        //given
        super.memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        String url = String.format("/member/%s", UUID.randomUUID());

        //when-then
        super.mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}
