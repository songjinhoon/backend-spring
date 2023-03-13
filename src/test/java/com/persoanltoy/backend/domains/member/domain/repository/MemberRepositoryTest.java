package com.persoanltoy.backend.domains.member.domain.repository;

import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.dto.request.SignUpDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("batch query test")
    void delete() {
        //given
        List<Member> members = IntStream.range(0, 10)
                .mapToObj(data -> {
                    SignUpDto signUpDto = SignUpDto.builder()
                            .username("hijinhoon" + data + "@naver.com")
                            .password("password")
                            .nickName("hijinhoon" + data)
                            .build();
                    return Member.create(signUpDto, passwordEncoder);
                })
                .collect(Collectors.toList());
        this.memberRepository.saveAll(members);
//        List<UUID> collect = members.stream().map(Member::getId).map(MemberId::).collect(Collectors.toList());

        //when
//        this.memberRepository.deleteAllByIdInBatch(collect);
    }

}