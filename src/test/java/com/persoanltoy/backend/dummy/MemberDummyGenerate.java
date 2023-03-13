package com.persoanltoy.backend.dummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persoanltoy.backend.config.AuthConstants;
import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.domain.entity.MemberNo;
import com.persoanltoy.backend.domains.member.domain.repository.MemberRepository;
import com.persoanltoy.backend.domains.member.dto.request.SignInDto;
import com.persoanltoy.backend.domains.member.dto.request.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class MemberDummyGenerate {

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String USERNAME = "hijinhoon@naver.com";

    private static final String PASSWORD = "password";

    public MemberNo generate() {
        Member save = memberRepository.save(Member.create(SignUpDto.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .nickName("hijinhoon")
                .build(), passwordEncoder));
        entityManager.flush();
        entityManager.clear();
        return save.getNumber();
    }

    public String getAccessToken() throws Exception {
        final SignInDto signInDto = SignInDto.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
        ResultActions resultActions = this.mockMvc.perform(get("/member/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signInDto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists(AuthConstants.AUTHORIZATION_HEADER));
        return resultActions.andReturn().getResponse().getHeader(AuthConstants.AUTHORIZATION_HEADER);
    }

    public List<MemberNo> generate(int i) {
        List<Member> members = IntStream.range(0, i)
                .mapToObj(data -> {
                    SignUpDto signUpDto = SignUpDto.builder()
                            .username("hijinhoon" + data + "@naver.com")
                            .password("password")
                            .nickName("hijinhoon" + data)
                            .build();
                    return Member.create(signUpDto, passwordEncoder);
                })
                .collect(Collectors.toList());
        memberRepository.saveAll(members);
        return members.stream().map(Member::getNumber).collect(Collectors.toList());
    }
}
