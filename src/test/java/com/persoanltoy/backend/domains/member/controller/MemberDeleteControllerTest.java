package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.common.BaseTest;
import com.persoanltoy.backend.domains.member.domain.entity.MemberNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberDeleteControllerTest extends BaseTest {

    @Test
    @DisplayName("delete")
    void deleteSuccess() throws Exception {
        //given
        MemberNo generate = super.memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        String url = String.format("/member/%s", generate);

        //when-then
        super.mockMvc.perform(delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(
                        document("member-delete")
                )
        ;
    }

    @Test
    @DisplayName("deleteAll")
    void deleteAllSuccess() throws Exception {
        //given
        super.memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        List<String> generate = super.memberDummyGenerate.generate(100).stream().map(MemberNo::toString).collect(Collectors.toList());
        String url = String.format("/member/delete/%s", String.join(",", generate));

        //when-then
        super.mockMvc.perform(delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(
                        document("member-delete-all")
                )
        ;
    }

    @Test
    @DisplayName("delete fail not found")
    void deleteFailNotFound() throws Exception {
        //given
        MemberNo generate = memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        String url = String.format("/member/%s", UUID.randomUUID());

        //when-then
        super.mockMvc.perform(delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

}
