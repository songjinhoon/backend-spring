package com.persoanltoy.backend.domain.usr.repository;

import com.persoanltoy.backend.domain.usr.entity.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepository extends JpaRepository<Usr, String> {
}
