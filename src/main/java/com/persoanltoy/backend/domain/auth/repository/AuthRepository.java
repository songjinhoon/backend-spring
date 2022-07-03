package com.persoanltoy.backend.domain.auth.repository;

import com.persoanltoy.backend.domain.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String> {
}
