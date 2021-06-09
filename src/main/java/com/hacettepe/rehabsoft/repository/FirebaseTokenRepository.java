package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.FirebaseToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirebaseTokenRepository extends JpaRepository<FirebaseToken,Long> {
}
