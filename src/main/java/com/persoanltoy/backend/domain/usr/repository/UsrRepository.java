package com.persoanltoy.backend.domain.usr.repository;

import com.persoanltoy.backend.domain.usr.entity.Usr;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsrRepository extends JpaRepository<Usr, String>, UsrRepositoryCustom {

    @EntityGraph(attributePaths = "auths")
    Optional<Usr> findOneWithAuthsById(String id);

    @EntityGraph(attributePaths = "auths")
    Optional<Usr> findOneWithAuthsByNm(String nm);

}
