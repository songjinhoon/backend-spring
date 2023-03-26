package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.common.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderFindControllerTest extends BaseTest {

    private String memberId;

    private String accessToken;

    @BeforeEach
    void baseProcess() throws Exception {
        this.memberId = super.memberDummyGenerate.generate();
        this.accessToken = super.memberDummyGenerate.getAccessToken();
    }

    @Test
    @DisplayName("find")
    void find() throws Exception {
        //given
        List<String> orderIds = super.orderDummyGenerate.generate(10, this.memberId);
        String url = String.format("/order/%s", orderIds.get(0));

        //when-then
        super.mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.accessToken)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "order-find"
                        )
                )
        ;
    }

    @Test
    @DisplayName("query")
    void query() throws Exception {
        //given
        List<String> generate = super.orderDummyGenerate.generate(100, this.memberId);
        String url = "/order";
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("page", "2");
        multiValueMap.add("size", "10");
        multiValueMap.add("sort", "rgsDt,desc");
        multiValueMap.add("sort", "updDt");
        multiValueMap.add("ordererName", "");
        multiValueMap.add("receiverName", "무명");

        //when-then
        super.mockMvc.perform(get(url)
                        .params(multiValueMap)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.accessToken)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "order-query",
                                requestParameters(
                                        parameterWithName("page").description("페이지번호"),
                                        parameterWithName("size").description("페이지크기"),
                                        parameterWithName("sort").description("정렬"),
                                        parameterWithName("ordererName").description("주문자 이름"),
                                        parameterWithName("receiverName").description("수령인 이름")
                                )
                        )
                )
        ;
    }

}