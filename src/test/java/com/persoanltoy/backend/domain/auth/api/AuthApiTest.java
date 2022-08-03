package com.persoanltoy.backend.domain.auth.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persoanltoy.backend.common.response.ResponseMessage;
import com.persoanltoy.backend.domain.auth.dto.SignUpDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void signup_valid_empty_param() throws Exception {
        //given
        SignUpDto signUpDto = SignUpDto.builder().build();

        //when - then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDto)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("code").value(ResponseMessage.NOT_VALID_REQUEST_DATA_EXCEPTION.getCode()))
                .andExpect(jsonPath("message").value(ResponseMessage.NOT_VALID_REQUEST_DATA_EXCEPTION.getValue()));
    }

    @Test
    void signup_valid_all_param() throws Exception {
        //given
        SignUpDto signUpDto = SignUpDto.builder()
                .id("")
                .pwd("")
                .nm("")
                .authId("")
                .build();

        //when - then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDto)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("code").value(ResponseMessage.NOT_VALID_REQUEST_DATA_EXCEPTION.getCode()))
                .andExpect(jsonPath("message").value(ResponseMessage.NOT_VALID_REQUEST_DATA_EXCEPTION.getValue()));
    }

    @Test
    void signup_valid_lack_param() throws Exception {
        //given
        SignUpDto signUpDto = SignUpDto.builder()
                .id("")
                .pwd("")
                .nm("")
                .build();

        //when - then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDto)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("code").value(ResponseMessage.NOT_VALID_REQUEST_DATA_EXCEPTION.getCode()))
                .andExpect(jsonPath("message").value(ResponseMessage.NOT_VALID_REQUEST_DATA_EXCEPTION.getValue()));
    }

    @Test
    void signup_illegalArgument() throws Exception {
        //given
        SignUpDto signUpDto = SignUpDto.builder()
                .id("testid@jinhoon.com")
                .pwd("password")
                .nm("testuser")
                .authId("ROLE_TEST")
                .build();

        //when
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDto)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("code").value(ResponseMessage.ERROR_NOT_FOUND.getCode()))
                .andExpect(jsonPath("message").value(ResponseMessage.ERROR_NOT_FOUND.getValue()));

        //then
    }

    @Test
    void signup_success() throws Exception {
        //given
        SignUpDto signUpDto = SignUpDto.builder()
                .id("testid@jinhoon.com")
                .pwd("password")
                .nm("testuser")
                .authId("ROLE_USER")
                .build();

        //when
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(ResponseMessage.SUCCESS_CREATE.getCode()))
                .andExpect(jsonPath("message").value(ResponseMessage.SUCCESS_CREATE.getValue()));

        //then
    }

}