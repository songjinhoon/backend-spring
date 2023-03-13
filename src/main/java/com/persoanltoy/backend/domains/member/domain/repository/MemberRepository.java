package com.persoanltoy.backend.domains.member.domain.repository;

import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.domain.entity.MemberNo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, MemberNo>, MemberRepositoryCustom {

    //    @EntityGraph(attributePaths = "auths")
    Optional<Member> findByUsername(String username);

    //    @EntityGraph(attributePaths = "auths")
    Optional<Member> findByNickName(String nickName);

}
